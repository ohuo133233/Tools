package com.wang.javatools.net.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Parcelable;
import android.util.Log;

public class WifiReceiver extends BroadcastReceiver {
    private final String TAG = "WifiReceiver";
    private WifiCallBack mWifiCallBack;


    public void setWifiCallBack(WifiCallBack mWifiCallBack) {
        this.mWifiCallBack = mWifiCallBack;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action == null) {
            Log.d(TAG, "action==null");
            return;
        }
        switch (intent.getAction()) {
            case WifiManager.WIFI_STATE_CHANGED_ACTION:
                // wifi打开与否
                int wifistate = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_DISABLED);
                if (wifistate == WifiManager.WIFI_STATE_DISABLED) {
                    Log.d(TAG, "系统关闭wifi");
                    if (mWifiCallBack != null) {
                        mWifiCallBack.closeWifi();
                    }
                } else if (wifistate == WifiManager.WIFI_STATE_ENABLED) {
                    Log.d(TAG, "系统开启wifi");
                    if (mWifiCallBack != null) {
                        mWifiCallBack.openWifi();
                    }
                }
                break;
            case ConnectivityManager.CONNECTIVITY_ACTION:
                Log.d(TAG, "网络发生了改变");

                break;
        }
    }
}