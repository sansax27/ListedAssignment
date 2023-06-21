package com.example.listedassignment.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class OpenInAppDataModel(
    @Json(name = "status") val status: Boolean,
    @Json(name = "statusCode") val statusCode: Int,
    @Json(name = "message") val statusMessage: String,
    @Json(name = "support_whatsapp_number") val supportWhatsAppNumber: String,
    @Json(name = "extra_income") val extraIncome: Double,
    @Json(name = "total_links") val totalLinks: Int,
    @Json(name = "total_clicks") val totalClicks: Int,
    @Json(name = "today_clicks") val todayClicks: Int,
    @Json(name = "top_source") val topSource: String,
    @Json(name = "top_location") val topLocation: String,
    @Json(name = "startTime") val startTime: String,
    @Json(name = "links_created_today") val linksCreatedToday: Int,
    @Json(name = "applied_campaign") val appliedCampaign: Int,
    @Json(name = "data") val data: LinksDataDataModel
)
