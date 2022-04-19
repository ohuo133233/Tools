package com.wang.tools.wifi

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.wang.javatools.log.LogUtils
import com.wang.javatools.net.wifi.WiFIToolsManager
import com.wang.javatools.permission.PermissionManager
import com.wang.javatools.widget.toast.ToastUtils
import com.wang.tools.R

class WIFIActivity : AppCompatActivity(), View.OnClickListener {
    private val TAG = "WIFIActivity";
    private var isEnabled = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wifiactivity)

        val getPermission = findViewById<Button>(R.id.get_permission)
        val getSSID = findViewById<Button>(R.id.get_ssid)
        val getWifiState = findViewById<Button>(R.id.get_wifi_state)
        val setWifiEnabled = findViewById<Button>(R.id.set_wifi_enabled)

        getPermission.setOnClickListener(this)
        getSSID.setOnClickListener(this)
        getWifiState.setOnClickListener(this)
        setWifiEnabled.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v == null) {
            return
        }

        when (v.id) {
            R.id.get_permission -> getPermission()
            R.id.get_ssid -> getSSID()
            R.id.get_wifi_state -> getWifiState()
            R.id.set_wifi_enabled -> setWifiEnabled()

        }
    }

    private fun setWifiEnabled() {
        var wiFIToolsManager = WiFIToolsManager()
        isEnabled = !isEnabled
        wiFIToolsManager.openWifi(this)
        Log.d(TAG, "修改WIFI状态: $isEnabled")
        getWifiState()
    }

    private fun getWifiState() {
        var wiFIToolsManager = WiFIToolsManager()
        val wifiState = wiFIToolsManager.getWifiState()
        ToastUtils.showLongToast(this, "wifiState: $wifiState")
    }

    private fun getSSID() {
        var wiFIToolsManager = WiFIToolsManager()
        val ssid = wiFIToolsManager.getSSID()
        ToastUtils.showLongToast(this, "ssid: $ssid")
        LogUtils.e(TAG, "ssid: $ssid")
    }

    private fun getPermission() {
        val permissionManager = PermissionManager(this)
        permissionManager.requestPermission(Manifest.permission.ACCESS_FINE_LOCATION, null);
    }
}