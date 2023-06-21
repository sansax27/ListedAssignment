package com.example.listedassignment.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listedassignment.data.models.LinksDataModel
import com.example.listedassignment.databinding.FragmentLinksTabsContentBinding
import com.example.listedassignment.adapter.rv.LinksDataRVAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LinksTabsContentFragment (private val linkSrcData: List<LinksDataModel>): Fragment() {


    private val binding: FragmentLinksTabsContentBinding by lazy {
        FragmentLinksTabsContentBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            linksTabsContentRv.apply {
                layoutManager = LinearLayoutManager(view.context).apply {
                    orientation = LinearLayoutManager.VERTICAL
                }
                adapter = LinksDataRVAdapter(linkSrcData)
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }
}