package com.wang.tools.wifi

import android.Manifest
import android.content.Intent
import android.net.wifi.ScanResult
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wang.javatools.base.BaseActivity
import com.wang.javatools.log.LogUtils
import com.wang.javatools.net.wifi.WiFIToolsManager
import com.wang.javatools.net.wifi.WifiCallBack
import com.wang.javatools.permission.IPermissionCallBack
import com.wang.javatools.permission.PermissionManager
import com.wang.javatools.widget.recyclerview.CommonRecyclerViewAdapter
import com.wang.javatools.widget.toast.ToastUtils
import com.wang.tools.R


class WIFIActivity : BaseActivity(), View.OnClickListener {
    private lateinit var mutableList: MutableList<ScanResult>
    private lateinit var mRecyclerViewAdapter: CommonRecyclerViewAdapter<ScanResult>
    private lateinit var mWifiListRecyclerView: RecyclerView
    private var isEnabled = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wifi_activity)
        initView()

        mutableList = mutableListOf()
        initRecyclerView()
    }


    private fun initView() {
        val getPermission = findViewById<Button>(R.id.get_permission)
        val getWifiState = findViewById<Button>(R.id.get_wifi_state)
        val setWifiEnabled = findViewById<Button>(R.id.set_wifi_enabled)
        val updateAndGetWifiList = findViewById<Button>(R.id.update_and_get_wifi_list)
        val monitorWIFIStatus = findViewById<Button>(R.id.monitor_wifi_status)
        mWifiListRecyclerView = findViewById(R.id.wifi_list)

        getPermission.setOnClickListener(this)
        getWifiState.setOnClickListener(this)
        setWifiEnabled.setOnClickListener(this)
        monitorWIFIStatus.setOnClickListener(this)
        updateAndGetWifiList.setOnClickListener(this)
    }

    private fun initRecyclerView() {
        mRecyclerViewAdapter = CommonRecyclerViewAdapter.Build<ScanResult>()
            .setContext(this)
            .setLayoutId(R.layout.recycler_wifi_item)
            .setDataList(mutableList)
            .build()

        mWifiListRecyclerView.layoutManager = LinearLayoutManager(this)
        mWifiListRecyclerView.adapter = mRecyclerViewAdapter

    }


    private fun updateRecyclerView() {
//        if (mutableList.size == 0) {
//            return;
//        }
        mRecyclerViewAdapter.setBaseRecyclerViewAdapterBackCall { holder, position ->

//            LogUtils.b(TAG, "WIFI " + this.mutableList[position].SSID)
//            LogUtils.b(TAG, "下标 " + position)

            // 获取WIFI name显示
            holder.getView<TextView>(R.id.wifi_name_value).text = this.mutableList[position].SSID
            if (WiFIToolsManager.getInstance().isConnectWifi(this.mutableList[position].SSID)) {
                holder.getView<TextView>(R.id.connect)
                    .setBackgroundColor(resources.getColor(android.R.color.holo_red_dark))
                holder.getView<TextView>(R.id.connect).text = "当前连接"
            } else {
                holder.getView<TextView>(R.id.connect)
                    .setBackgroundColor(resources.getColor(R.color.purple_500))
                holder.getView<TextView>(R.id.connect).text = "连接"
            }


            holder.getItemView().setOnClickListener {
                val intent = Intent(this, WIFIMessageActivity::class.java)
                intent.putExtra("WIFI_SSID", this.mutableList.get(position).SSID)
                intent.putExtra("WIFI_BSSID", this.mutableList.get(position).BSSID)
                intent.putExtra("WIFI_CAPABILITIES", this.mutableList.get(position).capabilities)
                intent.putExtra("WIFI_LEVEL", this.mutableList.get(position).level)
                intent.putExtra("WIFI_FREQUENCY", this.mutableList.get(position).frequency)
                intent.putExtra("WIFI_TIMESTAMP", this.mutableList.get(position).timestamp)
                startActivity(intent)
            }
        }
    }

    override fun onClick(v: View?) {
        if (v == null) {
            return
        }

        when (v.id) {
            R.id.get_permission -> getPermission()
            R.id.get_wifi_state -> getWifiState()
            R.id.set_wifi_enabled -> setWifiEnabled()
            R.id.update_and_get_wifi_list -> getWifiList()
            R.id.monitor_wifi_status -> monitorWIFIStatus()

        }
    }

    private fun monitorWIFIStatus() {
        WiFIToolsManager.getInstance().registerReceiver(this, object : WifiCallBack {
            override fun openWifi() {
                LogUtils.d(TAG, "openWifi")
            }

            override fun closeWifi() {
                LogUtils.d(TAG, "closeWifi")
            }
        })
    }


    private fun setWifiEnabled() {
        isEnabled = !isEnabled
        if (isEnabled) {
            WiFIToolsManager.getInstance().openWifi(this)
        } else {
            WiFIToolsManager.getInstance().colesWifi(this)
        }

        Log.d(TAG, "修改WIFI状态: $isEnabled")
        getWifiState()
    }

    private fun getWifiState() {
        val wifiState = WiFIToolsManager.getInstance().getWifiState()
        ToastUtils.showLongToast(this, "wifiState: $wifiState")
    }

    private fun getPermission() {
        val permissionManager = PermissionManager(this)
        permissionManager.requestPermissions(
            object : IPermissionCallBack {
                override fun success() {
                    ToastUtils.showShortToast(this@WIFIActivity, "success")
                }

                override fun fail() {
                    ToastUtils.showShortToast(this@WIFIActivity, "fail")
                }

                override fun noMoreReminders() {
                    ToastUtils.showShortToast(this@WIFIActivity, "noMoreReminders")
                }

                override fun alreadyObtainedPermission() {
                    ToastUtils.showShortToast(this@WIFIActivity, "alreadyObtainedPermission")
                }
            },
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }

    private fun getWifiList() {
        mutableList.addAll(WiFIToolsManager.getInstance().getWifiList())
        updateRecyclerView()
        mRecyclerViewAdapter.notifyDataSetChanged()
    }
}