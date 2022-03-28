package com.wang.javatools.net;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;


/**
 * Created by Carson_Ho on 16/10/31.
 */
public class NetWorkStateReceiver extends BroadcastReceiver {
    private static final String TAG = "NetWorkStateReceiver";
    private INetworkStateChanges mINetworkStateChanges;

    public NetWorkStateReceiver(INetworkStateChanges iNetworkStateChanges) {
        mINetworkStateChanges = iNetworkStateChanges;
    }

    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "网络状态发生变化");
        ConnectivityManager connMgr;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo wifiNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            NetworkInfo dataNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (!wifiNetworkInfo.isConnected() && !dataNetworkInfo.isConnected()) {
                Log.d(TAG, "wifi 和 移动数据 均未连接");
                mINetworkStateChanges.onNetDisconnect();
            } else {
                Log.d(TAG, "wifi 或 移动数据 已连接");
                mINetworkStateChanges.onNetConnect();
            }
        } else {
            connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            Network[] networks = connMgr.getAllNetworks();
            boolean hasConnected = false;
            for (Network network : networks) {
                NetworkInfo networkInfo = connMgr.getNetworkInfo(network);
                if (networkInfo.isConnected()) {
                    Log.d(TAG, "wifi 或 移动数据 已连接");
                    hasConnected = true;
                    mINetworkStateChanges.onNetConnect();
                }
            }
            if (!hasConnected) {
                Log.d(TAG, "wifi 和 移动数据 均未连接");
            }
        }
    }
}



