package com.wang.javatools.net.wifi;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.wang.javatools.widget.toast.ToastUtils;

public class WiFIManager {

    public String getSSID(AppCompatActivity context) {
        String SSID = null;
        switch (Build.VERSION.SDK_INT) {
            case Build.VERSION_CODES.P:
                if (checkGpsIsOpen(context)) {
                    ConnectivityManager ctm = (ConnectivityManager) context.getApplicationContext()
                            .getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo networkInfo = ctm.getActiveNetworkInfo();
                    SSID = networkInfo.getExtraInfo();
                }

                break;
            case Build.VERSION_CODES.Q:
                ToastUtils.showToast(context, "暂不支持");
                break;
            default:
                ConnectivityManager ctm = (ConnectivityManager) context.getApplicationContext()
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = ctm.getActiveNetworkInfo();
                return networkInfo.getExtraInfo();

        }
        return SSID;


    }

    private boolean checkGpsIsOpen(Context context) {
        boolean isOpen;
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        isOpen = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        Log.d("TAG", "Gps is Open = " + isOpen);
        return isOpen;
    }


}
