package com.example.listedassignment

import java.text.SimpleDateFormat
import java.util.*

object Utils {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)


    fun parseStringDate(date: String): Date? {
        return try {
            dateFormat.parse(date)
        } catch (e: Exception) {
            null
        }
    }
}