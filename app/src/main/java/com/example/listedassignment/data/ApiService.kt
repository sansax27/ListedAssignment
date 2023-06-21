package com.example.listedassignment.data

import com.example.listedassignment.data.models.OpenInAppDataModel
import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Singleton


@Singleton
interface ApiService {

    @GET(".")
    suspend fun getOpenInAppData(): Response<OpenInAppDataModel>

}