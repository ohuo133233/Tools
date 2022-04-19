package com.wang.javatools.manager;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.wang.javatools.base.ScreenOrientation;

public class AppManager {
    private static final String TAG = AppManager.class.getSimpleName();
    private Context mApplicationContext;
    private int orientation;
    private ScreenOrientation mScreenOrientation;

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
    // TODO
    public void setDebug() {

    }

    /**
     * 设置横竖屏切换，并通知监听的接口回调
     *
     * @param orientation 方向
     */
    public void setOrientation(int orientation) {
        this.orientation = orientation;
        if (mScreenOrientation != null) {
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                mScreenOrientation.landscape();
            } else {
                mScreenOrientation.portrait();
            }
        }
    }

    public int getOrientation() {
        return orientation;
    }

    public void setScreenOrientation(ScreenOrientation mScreenOrientation) {
        this.mScreenOrientation = mScreenOrientation;
    }
}
