package com.wang.javatools.net.wifi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import com.wang.javatools.manager.AppManager;

public class WiFIToolsManager {

    private static final String TAG = "WiFIToolsManager";
    private ConnectivityManager mConnectivityManager;
    private WifiManager mWifiManager;

    public String getSSID() {
        switch (Build.VERSION.SDK_INT) {
            case Build.VERSION_CODES.P:
                Log.d(TAG, "使用28的API获取");
                if (checkGpsIsOpen()) {
                    getConnectivityManager();
                    NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();
                    return networkInfo.getExtraInfo();
                }
            case Build.VERSION_CODES.Q:
                Log.d(TAG, "使用29的API");
                //获取
                getWifiManager();
                WifiInfo connectionInfo = mWifiManager.getConnectionInfo();
                return connectionInfo.getSSID();
            default:
                Log.d(TAG, "使用低于28的API");
                getConnectivityManager();
                NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();
                return networkInfo.getExtraInfo();
        }
    }

    public int getWifiState() {
        WifiManager wifiManager = (WifiManager) AppManager.getInstance().getApplicationContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        return wifiManager.getWifiState();
    }

    /**
     * Android10一下支持自动打开WIFI
     * Android10以上只能打开设置面板（部分手机只支持打开WIFI设置页面，无法打开WIFI悬浮UI）
     *
     * @param activity
     */
    public void openWifi(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Intent intent = new Intent();
            intent.setAction(Settings.Panel.ACTION_WIFI);
            activity.startActivity(intent);
        } else {
            getWifiManager();
            mWifiManager.setWifiEnabled(true);
        }
    }

    private void getWifiManager() {
        if (mWifiManager == null) {
            mWifiManager = (WifiManager) AppManager.getInstance().getApplicationContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        }
    }

    private void getConnectivityManager() {
        if (mConnectivityManager == null) {
            mConnectivityManager = (ConnectivityManager) AppManager.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        }
    }

    private boolean checkGpsIsOpen() {
        LocationManager locationManager = (LocationManager) AppManager.getInstance().getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        boolean isOpen = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        Log.d(TAG, "Gps is Open = " + isOpen);
        return isOpen;
    }


//    public boolean is24GHzBandSupported(Context context) {
//        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//        return wifiManager.is24GHzBandSupported();
//    }
//
//    public boolean is5GHzBandSupported(Context context) {
//        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//        return wifiManager.is5GHzBandSupported();
//    }
//
//    public boolean is60GHzBandSupported(Context context) {
//        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//        return wifiManager.is60GHzBandSupported();
//    }


}
