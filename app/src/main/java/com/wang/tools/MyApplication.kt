package com.wang.tools

import android.app.Application
import android.app.Notification
import com.wang.javatools.manager.JavaToolsManager

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val javaToolsManager = JavaToolsManager.Build()
            .setIsDebug(BuildConfig.DEBUG)
            .build()



    }
}