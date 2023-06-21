package com.example.listedassignment.data.models

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ChartDataDataModel(val date: String, val clicks: String)