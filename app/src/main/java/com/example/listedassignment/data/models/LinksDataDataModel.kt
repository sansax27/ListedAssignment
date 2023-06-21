package com.example.listedassignment.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class LinksDataDataModel(
    @Json(name = "recent_links") val recentLinks: List<LinksDataModel>,
    @Json(name = "top_links") val topLinks: List<LinksDataModel>,
    @Json(name = "overall_url_chart") val overAllURLChart: Map<String, Int>
)

