package com.mucheng.leafide.application

import android.app.Application

class AppContext : Application() {

    companion object {

        @JvmStatic
        lateinit var instance: AppContext
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
