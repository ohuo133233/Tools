package com.wang.javatools.manager;

import android.app.Application;
import android.content.Context;

// TODO 第一次启动方法待完善
public class AppManager {
    private Context mApplicationContext;

    private AppManager() {

    }

    public static AppManager getInstance() {
        return AppManagerHolder.instance;
    }


    private static class AppManagerHolder {
        private final static AppManager instance = new AppManager();
    }

    public void setApplicationContext(Context applicationContext) {
        if (applicationContext instanceof Application) {
            mApplicationContext = applicationContext;
        } else {
            throw new IllegalArgumentException("需要使用applicationContext");
        }

    }

    public Context getApplicationContext() {
        return mApplicationContext;
    }

    /**
     * 设置为Debug模式
     */
    public void setDebug() {

    }


}
