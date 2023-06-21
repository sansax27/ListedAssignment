package com.example.listedassignment.adapter.rv

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.listedassignment.Utils
import com.example.listedassignment.data.models.LinksDataModel
import com.example.listedassignment.databinding.LinksRvItemBinding
import java.text.SimpleDateFormat
import java.util.*

class LinksDataRVAdapter(private val linksDataModel: List<LinksDataModel>): RecyclerView.Adapter<LinksDataRVAdapter.LinksDataViewHolder>() {


    private val dateFormatter = SimpleDateFormat("d MMM yyyy", Locale.US)
    inner class LinksDataViewHolder(binding: LinksRvItemBinding): RecyclerView.ViewHolder(binding.root) {
        val linksSrcIV = binding.linksSrcIv
        val linksSrcNameTV = binding.linksSrcNameTv
        val linksSrcDateTV = binding.linksSrcDateTv
        val linksNoClicksTV = binding.linksNoClicksTv
        val linksUrlTV = binding.linksUrlTv
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinksDataViewHolder {
        return LinksDataViewHolder(LinksRvItemBinding.
        inflate(LayoutInflater.from(parent.rootView.context), null, false))
    }

    override fun getItemCount(): Int {
        return linksDataModel.size
    }

    override fun onBindViewHolder(holder: LinksDataViewHolder, position: Int) {
        holder.apply {
            val data = linksDataModel[position]
            linksSrcIV.load(data.thumbnail ?: data.originalImage)
            linksSrcNameTV.text = data.title
            linksNoClicksTV.text = data.totalClicks
            linksSrcDateTV.text = dateFormatter.format(Utils.parseStringDate(data.createdAt.split("T")[0])!!)
            linksUrlTV.text = data.webLink
            itemView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
    }
}