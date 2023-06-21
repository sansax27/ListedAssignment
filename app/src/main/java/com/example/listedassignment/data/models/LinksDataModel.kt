package com.example.listedassignment.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class LinksDataModel(
    @Json(name = "url_id") val urlID: Int,
    @Json(name = "web_link") val webLink: String,
    @Json(name = "smart_link") val smartLink: String,
    @Json(name = "title") val title: String,
    @Json(name = "total_clicks") val totalClicks: String,
    @Json(name = "original_image") val originalImage: String,
    @Json(name = "thumbnail") val thumbnail: String?,
    @Json(name = "times_ago") val timesAgo: String,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "domain_id") val domainID: String,
    @Json(name = "url_prefix") val urlPrefix: String?,
    @Json(name = "url_suffix") val urlSuffix: String,
    @Json(name = "app") val app: String
)
