package com.demo.shaadi.utils

import android.app.Application
import com.zplesac.connectionbuddy.ConnectionBuddy
import com.zplesac.connectionbuddy.ConnectionBuddyConfiguration

class UserApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        configureLiveConnection()
    }

    private fun configureLiveConnection() {
        val networkInspectorConfiguration = ConnectionBuddyConfiguration.Builder(this).build()
        ConnectionBuddy.getInstance().init(networkInspectorConfiguration)
    }
}