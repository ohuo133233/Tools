package com.wang.javatools.net.wifi;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.MacAddress;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkSpecifier;
import android.os.Build;
import android.os.PatternMatcher;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.wang.javatools.BuildConfig;
import com.wang.javatools.manager.AppManager;

import java.util.List;

/**
 * 使用单列的类对象来获取功能
 */
public class WiFIToolsManager {

    private static final String TAG = "WiFIToolsManager";
    private ConnectivityManager mConnectivityManager;
    private WifiManager mWifiManager;

    /* ************************Start******************************/
    /*                 单例和构造方法和私有方法                      */

    private WiFIToolsManager() {
        getWifiManager();
        getConnectivityManager();
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


    public static WiFIToolsManager getInstance() {
        return WiFIToolsManagerHolder.sInstance;
    }


    private static class WiFIToolsManagerHolder {
        private static final WiFIToolsManager sInstance = new WiFIToolsManager();
    }

    /* ************************END******************************/


    /**
     * 根据不同API等级，获取WIFI的SSID
     * <p>
     * 调用此方法需要保证有运行时权限
     * {@link android.Manifest.permission#ACCESS_FINE_LOCATION}、WIFI状态是开启和状态栏GPS打开状态
     *
     * @return WIFI的SSID
     */
    public String getSSID() {
        if (!getWifiState()) {
            if (BuildConfig.DEBUG) {
                throw new RuntimeException("未打开WIFI");
            } else {
                Log.e(TAG, "未打开WIFI");
                return "未打开WIFI";
            }
        }

        switch (Build.VERSION.SDK_INT) {
            case Build.VERSION_CODES.P:
                Log.d(TAG, "使用28的API获取");
                if (checkGpsIsOpen()) {
                    NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();
                    String extraInfo = networkInfo.getExtraInfo();
                    Log.d(TAG, "extraInfo: " + extraInfo);
                    return networkInfo.getExtraInfo();
                } else {
                    if (BuildConfig.DEBUG) {
                        throw new SecurityException("没有打开位置权限");
                    } else {
                        Log.e(TAG, "没有打开位置权限");
                        return "没有打开位置权限";
                    }
                }
            case Build.VERSION_CODES.Q:
                Log.d(TAG, "使用29的API");
                if (checkGpsIsOpen()) {
                    WifiInfo connectionInfo = mWifiManager.getConnectionInfo();
                    return connectionInfo.getSSID();
                } else {
                    if (BuildConfig.DEBUG) {
                        throw new SecurityException("没有打开位置权限");
                    } else {
                        Log.e(TAG, "没有打开位置权限");
                        return "没有打开位置权限";
                    }
                }
            default:
                Log.d(TAG, "使用低于28的API");
                NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();
                if (networkInfo == null) {
                    Log.e(TAG, "networkInfo==null ");
                    return null;
                }
                String extraInfo = networkInfo.getExtraInfo();
                if (extraInfo != null) {
                    Log.d(TAG, "extraInfo: " + extraInfo);
                }
                return networkInfo.getExtraInfo();
        }

    }


    /**
     * 获取当前WIFI状态
     *
     * @return WIFI当前的状态
     */
    public boolean getWifiState() {
        WifiManager wifiManager = (WifiManager) AppManager.getInstance().getApplicationContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wifiManager.getWifiState() == 3) {
            return true;
        }
        return false;
    }

    /**
     * 打开WIFI
     * Android10一下支持自动打开WIFI
     * Android10以上只能打开设置面板（部分手机只支持打开WIFI设置页面，无法打开WIFI悬浮UI）
     *
     * @param activity 弹窗悬浮窗或者跳转页面使用的Activity
     */
    public void openWifi(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Intent intent = new Intent();
            intent.setAction(Settings.Panel.ACTION_WIFI);
            activity.startActivity(intent);
        } else {
            mWifiManager.setWifiEnabled(true);
        }
    }

    /**
     * 关闭WIFI
     * Android10一下支持自动关闭WIFI
     * Android10以上只能打开设置面板（部分手机只支持打开WIFI设置页面，无法打开WIFI悬浮UI）
     *
     * @param activity 弹窗悬浮窗或者跳转页面使用的Activity
     */
    public void colesWifi(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Intent intent = new Intent();
            intent.setAction(Settings.Panel.ACTION_WIFI);
            activity.startActivity(intent);
        } else {
            mWifiManager.setWifiEnabled(false);
        }
    }


    /**
     * 检查是否打开GPS定位
     *
     * @return 是否打开GPS定位
     */
    private boolean checkGpsIsOpen() {
        LocationManager locationManager = (LocationManager) AppManager.getInstance().getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        boolean isOpen = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        Log.d(TAG, "Gps is Open = " + isOpen);
        return isOpen;
    }

    /**
     * 获取WIFI列表
     */
    public List<ScanResult> getWifiList() {
        List<ScanResult> scanResults = mWifiManager.getScanResults();
        Log.d(TAG, "WIFI list" + scanResults.toString());
        return scanResults;

    }

    /**
     * 连接指定的WIFI
     * 连接步骤：
     * 1.判断是否打开WIFI
     * 2.判断是否连接WIFI中
     * 3.连接
     *
     * @param ssid     目标SSID
     * @param password 目标密码
     * @param timeOut  超时时间
     */
    public void connectWifi(String ssid, String bssid, String capabilities, String password, int timeOut) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

            WifiNetworkSpecifier wifiNetworkSpecifier = new WifiNetworkSpecifier.Builder()
                    .setSsidPattern(new PatternMatcher(ssid, PatternMatcher.PATTERN_PREFIX))
                    .setWpa2Passphrase(password)
                    .build();

            NetworkRequest networkRequest = new NetworkRequest.Builder()
                    .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                    .removeCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    .setNetworkSpecifier(wifiNetworkSpecifier)
                    .build();

            mConnectivityManager.requestNetwork(networkRequest, new ConnectivityManager.NetworkCallback() {
                @Override
                public void onAvailable(@NonNull Network network) {
                    super.onAvailable(network);
                    Log.d(TAG, "network: " + network.toString());
                }
            });

        } else {
            if (!getWifiState()) {
                Log.e(TAG, "请打开WIFI");
            }
            Log.d(TAG, "ssid: " + ssid);
            Log.d(TAG, "password: " + password);

            mWifiManager.disconnect();
            WifiConfiguration config = new WifiConfiguration();
            // wifi SSID
            config.SSID = "\"" + ssid + "\"";
            // wifi密码
            config.preSharedKey = "\"" + password + "\"";
            config.hiddenSSID = true;
            config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
            config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
            config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
            config.status = WifiConfiguration.Status.ENABLED;
            int netId = mWifiManager.addNetwork(config);
            boolean result = mWifiManager.enableNetwork(netId, true);
            Log.d(TAG, "result: " + result);
        }
    }

    public List<WifiConfiguration> getConfiguredNetworks(Context context) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.e(TAG, "没有权限");
            return null;
        }
        return mWifiManager.getConfiguredNetworks();
    }


    /**
     * 当前连接的WIFI是否指定连接的WIFI
     * 此方法会尝试加上“”对比
     *
     * @param ssid 指定连接的WIFI
     * @return 是否指定连接的WIFI
     */
    public boolean isConnectWifi(String ssid) {
        // 直接对比
        String targetSSID = getSSID();
        if (targetSSID == null) {
            Log.d(TAG, "当前没有连接WIFI");
            return false;
        }
        Log.d(TAG, "当前连接的: " + targetSSID);
        Log.d(TAG, "传入的: " + ssid);
        if (targetSSID.equals(ssid)) {
            return true;
        }
        // 上面对比失败，给传入的SSID加上""，再试试
        ssid = "\"" + ssid + "\"";

        if (targetSSID.equals(ssid)) {
            return true;
        }

        // 都无法匹配，返回不是
        return false;
    }

    public boolean isWifi(String targetSSID, String ssid) {
        // 直接对比
        Log.d(TAG, "直接对比");
        Log.d(TAG, "当前连接的: " + targetSSID);
        Log.d(TAG, "传入的: " + ssid);
        if (targetSSID.equals(ssid)) {
            return true;
        }
        // 上面对比失败，给传入的SSID加上""，再试试
        targetSSID = "\"" + targetSSID + "\"";
        Log.d(TAG, "再次对比");
        Log.d(TAG, "当前连接的: " + targetSSID);
        Log.d(TAG, "传入的: " + ssid);
        if (targetSSID.equals(ssid)) {
            return true;
        }

        // 都无法匹配，返回不是
        return false;
    }

    @Deprecated
    public boolean is24GHzBandSupported() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            return mWifiManager.is24GHzBandSupported();
        } else {
            return false;
        }
    }

    public boolean is5GHzBandSupported() {
        return mWifiManager.is5GHzBandSupported();
    }

    @Deprecated
    public boolean is60GHzBandSupported() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            return mWifiManager.is60GHzBandSupported();
        } else {
            return false;
        }
    }


}
