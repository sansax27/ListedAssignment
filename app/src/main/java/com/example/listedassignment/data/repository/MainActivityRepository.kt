package com.example.listedassignment.data.repository

import com.example.listedassignment.data.ApiService
import com.example.listedassignment.data.NetworkResult
import com.example.listedassignment.data.NetworkUtils
import com.example.listedassignment.data.models.OpenInAppDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


class MainActivityRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getOpenInAppData(): NetworkResult<OpenInAppDataModel> = withContext(Dispatchers.IO) { NetworkUtils.callApi { apiService.getOpenInAppData() } }

}