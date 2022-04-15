package com.wang.javatools.net.wifi;

import android.app.Application;
import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.util.Log;

public class WiFIToolsManager {

    private static final String TAG = "WiFIToolsManager";

    public String getSSID(Context applicationContext) {

        if (applicationContext instanceof Application) {
            throw new IllegalArgumentException("需要使用applicationContext");
        }

        switch (Build.VERSION.SDK_INT) {
            case Build.VERSION_CODES.P:
                Log.d(TAG, "使用28的API获取");
                if (checkGpsIsOpen(applicationContext)) {
                    ConnectivityManager connectivityManager = (ConnectivityManager) applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                    return networkInfo.getExtraInfo();
                }
            case Build.VERSION_CODES.Q:
                Log.d(TAG, "使用29的API");
                //获取
                WifiManager wifiManager = (WifiManager) applicationContext.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                return connectionInfo.getSSID();
            default:
                Log.d(TAG, "使用低于28的API");
                ConnectivityManager connectivityManager = (ConnectivityManager) applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                return networkInfo.getExtraInfo();
        }

    }

    private boolean checkGpsIsOpen(Context context) {
        boolean isOpen;
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        isOpen = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        Log.d(TAG, "Gps is Open = " + isOpen);
        return isOpen;
    }


//    public int getWifiState() {
//
//    }
//
//
//    public boolean is24GHzBandSupported() {
//    }
//
//    public boolean is5GHzBandSupported() {
//    }
//
//    public boolean is60GHzBandSupported() {
//    }


}
