
package com.wang.javatools.netstate;


public  interface NetCallback {

    void wifi(String wifiState);

    void noWifi(String wifiState);

    void mobile(String netWorkState);

    void noNet();
}
