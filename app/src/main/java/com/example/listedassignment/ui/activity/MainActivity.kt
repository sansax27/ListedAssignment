package com.example.listedassignment.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.core.view.size
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listedassignment.R
import com.example.listedassignment.Utils
import com.example.listedassignment.data.UIStatus
import com.example.listedassignment.data.models.LinksStatsDataModel
import com.example.listedassignment.databinding.ActivityMainBinding
import com.example.listedassignment.adapter.rv.LinksStatsRVAdapter
import com.example.listedassignment.adapter.viewpager.MainActivityLinksDataViewPagerAdapter
import com.example.listedassignment.viewmodel.MainActivityViewModel
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainActivityViewModel>()
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    val dashboardChartSDF = SimpleDateFormat("MMM dd", Locale.US)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.openInAppDataLiveData.observe(this) {

            Timber.e(it.toString())
            when(it) {
                is UIStatus.Success -> {
                    val currentDayHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
                    val dayGreetingsArray = resources.getStringArray(R.array.day_greetings_array)

                    // Name Not In API Response Right Now
                    binding.apply {
                        mainScreenUserNameTv.text = "Ajay Manav"
                        mainScreenDayGreetingTv.text = when(true) {
                            (currentDayHour in 6..12) -> dayGreetingsArray[0]
                            (currentDayHour in 12..17) -> dayGreetingsArray[1]
                            (currentDayHour in 17..19) -> dayGreetingsArray[2]
                            else -> dayGreetingsArray[3]
                        }
                        val chartData = it.data.data.overAllURLChart.toList()
                        mainScreenMphilLineChart.apply {
                            setPinchZoom(true)
                            setTouchEnabled(true)
                            xAxis.valueFormatter = object : ValueFormatter() {
                                override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                                    val position = value.roundToInt()
                                    return if (position < chartData.size) {
                                        try {
                                            dashboardChartSDF.format(Utils.parseStringDate(chartData[position].first)?.time)
                                        } catch (e:java.lang.Exception) {
                                            ""
                                        }
                                    } else {
                                        ""
                                    }
                                }
                            }
                            description.isEnabled = false
                            xAxis.position = XAxis.XAxisPosition.BOTTOM
                            xAxis.granularity = 1F
                            axisRight.isEnabled = false
                            val lineDataSet = LineDataSet(chartData
                                .mapIndexed { index, pair ->
                                    Entry(index.toFloat(), pair.second.toFloat()) }, "Overview")
                            lineDataSet.apply {
                                lineWidth = 3f
                                valueTextSize = 4f
                                mode = LineDataSet.Mode.CUBIC_BEZIER
                                color = getColor(R.color.black)
                                valueTextColor = getColor(R.color.purple_200)
                                enableDashedLine(20F, 10F, 0F)
                            }
                            legend.apply {
                                textColor = getColor(R.color.text_color)
                                horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
                                verticalAlignment = Legend.LegendVerticalAlignment.TOP
                                textSize = 50f
                            }
                            xAxis.spaceMin = 3f
                            data = LineData(listOf<ILineDataSet>(lineDataSet))
                            invalidate()
                        }
                        mainScreenAppStatsHrv.apply {
                            layoutManager = LinearLayoutManager(this@MainActivity).apply {
                                orientation = LinearLayoutManager.HORIZONTAL
                            }
                            adapter = LinksStatsRVAdapter(listOf(LinksStatsDataModel("Today's Clicks", it.data.todayClicks.toString()),
                                LinksStatsDataModel("Top Location", it.data.topLocation),
                                LinksStatsDataModel("Top Source", it.data.topSource)))
                        }
                        mainScreenLinksTabsLayout.addTab(mainScreenLinksTabsLayout.newTab())
                        mainScreenLinksTabsLayout.addTab(mainScreenLinksTabsLayout.newTab())
                        mainScreenLinksTabsVp.adapter = MainActivityLinksDataViewPagerAdapter(
                            supportFragmentManager, lifecycle, it.data.data.topLinks, it.data.data.recentLinks)
                        val linksTabArray = resources.getStringArray(R.array.links_tab_array)
                        TabLayoutMediator(mainScreenLinksTabsLayout, mainScreenLinksTabsVp) { tab, pos ->
                            mainScreenLinksTabsVp.currentItem = pos
                            tab.text = linksTabArray[pos]
                        }.attach()
                        binding.mainScreenPb.visibility = View.GONE
                        binding.mainScreenSv.visibility = View.VISIBLE
                    }

                }
                is UIStatus.Loading -> {}
                is UIStatus.Empty -> {}
                is UIStatus.Error -> {
                    Toast.makeText(baseContext, it.message, Toast.LENGTH_SHORT).show()
                    binding.mainScreenPb.visibility = View.GONE
                }
            }
        }
    }



}