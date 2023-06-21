package com.example.listedassignment

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class ListAssignmentApplication : Application() {

    companion object {

        private var appContext: Context? = null
        val token : String
        get() = appContext?.getSharedPreferences("Token Data", Context.MODE_PRIVATE)?.getString("Token", "") ?: ""
    }

    override fun onCreate() {
        super.onCreate()
        this.getSharedPreferences("Token Data", Context.MODE_PRIVATE).edit()
            .putString("Token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjU5MjcsImlhdCI6MTY3NDU1MDQ1MH0.dCkW0ox8tbjJA2GgUx2UEwNlbTZ7Rr38PVFJevYcXFI").apply()
        appContext = this
        Timber.plant(Timber.DebugTree())
    }

}