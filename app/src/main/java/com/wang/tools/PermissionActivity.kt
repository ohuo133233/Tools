package com.wang.tools

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wang.javatools.manager.permission.PermissionManager


class PermissionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)


        var permissionManager = PermissionManager(this)
        permissionManager.requestPermission(Manifest.permission.CAMERA)
        permissionManager.requestPermission(Manifest.permission.ACCEPT_HANDOVER)
        permissionManager.requestPermission(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
    }
}