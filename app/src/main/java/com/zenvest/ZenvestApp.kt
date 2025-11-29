package com.zenvest

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ZenvestApp : Application() {

    override fun onCreate() {
        super.onCreate()
        // Initialize any app-wide services here
    }
}
