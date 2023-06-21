package com.example.listedassignment.adapter.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.listedassignment.R
import com.example.listedassignment.data.models.LinksStatsDataModel
import com.example.listedassignment.databinding.LinksStatsItemBinding

class LinksStatsRVAdapter(private val linksStatsData: List<LinksStatsDataModel>): RecyclerView.Adapter<LinksStatsRVAdapter.LinksStatsViewHolder>() {

    inner class LinksStatsViewHolder(binding : LinksStatsItemBinding): RecyclerView.ViewHolder(binding.root) {
        val dataIV = binding.linksStatsIv
        val dataTV = binding.linksStatsDataTv
        val dataValueTV = binding.linksStatsDataValueTv
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinksStatsViewHolder {
        return LinksStatsViewHolder(LinksStatsItemBinding.
        inflate(LayoutInflater.from(parent.context), null, false))

    }

    override fun getItemCount(): Int {
        return linksStatsData.size
    }


    override fun onBindViewHolder(holder: LinksStatsViewHolder, position: Int) {
        holder.apply {
            dataTV.text = linksStatsData[position].dataSRC
            dataValueTV.text = linksStatsData[position].dataValue
            when(position) {
                0 -> dataIV.setImageDrawable(ResourcesCompat.getDrawable(itemView.resources, R.drawable.todays_clicks_icon, itemView.context.theme))
                1 -> dataIV.setImageDrawable(ResourcesCompat.getDrawable(itemView.resources, R.drawable.top_location_icon, itemView.context.theme))
                2 -> dataIV.setImageDrawable(ResourcesCompat.getDrawable(itemView.resources, R.drawable.top_source_icon, itemView.context.theme))
            }
        }
    }
}