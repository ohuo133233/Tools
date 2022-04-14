package com.wang.tools

import android.app.Application
import com.wang.javatools.log.LogUtils
import com.wang.javatools.manager.JavaToolsManager

class MyApplication : Application() {
    private val TAG = "MyApplication"
    override fun onCreate() {
        LogUtils.e(TAG, "MyApplication onCreate")
        super.onCreate()
        val javaToolsManager = JavaToolsManager.Build()
            .setIsDebug(BuildConfig.DEBUG)
            .setApplication(this)
            .build()
    }
}