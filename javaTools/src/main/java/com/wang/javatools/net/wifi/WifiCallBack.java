package com.wang.javatools.net.wifi;

public interface WifiCallBack {

    default void openWifi() {
    }

    default void closeWifi() {
    }

    default void connectWifiSuccess() {

    }

    default void connectWifiFail() {

    }
}
