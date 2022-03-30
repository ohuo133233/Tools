package com.wang.tools

import android.Manifest
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.wang.javatools.log.LogUtils
import com.wang.javatools.net.wifi.WiFIManager
import com.wang.javatools.permission.PermissionManager

class WIFIActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wifiactivity)

        val getPermission = findViewById<Button>(R.id.get_permission)
        val getSSID = findViewById<Button>(R.id.get_ssid)

        getPermission.setOnClickListener {
            val permissionManager = PermissionManager(this)
            permissionManager.requestPermission(
                Manifest.permission.ACCESS_BACKGROUND_LOCATION, null)
        }

        getSSID.setOnClickListener {
            var wifiManager = WiFIManager()
            val ssid = wifiManager.getSSID(this)
            LogUtils.e("TAG", "ssid: $ssid")
        }
    }
}