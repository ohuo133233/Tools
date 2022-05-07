package com.wang.tools.wifi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.wang.tools.R

class WIFIMessageActivity : AppCompatActivity() {
    private lateinit var wifiNameValue: TextView
    private lateinit var wifiBssidValue: TextView
    private lateinit var capabilitiesValue: TextView
    private lateinit var wifiLevelValue: TextView
    private lateinit var wifiFrequencyValue: TextView
    private lateinit var wifiTimestampValue: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wifi_message)

        initView()
        initData()
    }


    private fun initView() {
        wifiNameValue = findViewById(R.id.wifi_name_value)
        wifiBssidValue = findViewById(R.id.wifi_bssid_value)
        capabilitiesValue = findViewById(R.id.capabilities_value)
        wifiLevelValue = findViewById(R.id.wifi_Level_value)
        wifiFrequencyValue = findViewById(R.id.wifi_frequency_value)
        wifiTimestampValue = findViewById(R.id.wifi_timestamp_value)

    }


    private fun initData() {
        wifiNameValue.text = intent.getStringExtra("WIFI_SSID")
        wifiBssidValue.text = intent.getStringExtra("WIFI_BSSID")
        capabilitiesValue.text = intent.getStringExtra("WIFI_CAPABILITIES")
        wifiLevelValue.text = intent.getStringExtra("WIFI_LEVEL")
        wifiFrequencyValue.text = intent.getStringExtra("WIFI_FREQUENCY")
        wifiTimestampValue.text = intent.getStringExtra("WIFI_TIMESTAMP")
    }
}