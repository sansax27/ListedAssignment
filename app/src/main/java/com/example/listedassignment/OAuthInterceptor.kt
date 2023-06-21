package com.example.listedassignment

import android.app.Application
import android.content.SharedPreferences
import com.example.listedassignment.ListAssignmentApplication.Companion.token
import okhttp3.Interceptor
import okhttp3.Response

class OAuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        return chain.proceed(chain.request().newBuilder()
            .header("Authorization", "Bearer $token")
            .build())
    }
}