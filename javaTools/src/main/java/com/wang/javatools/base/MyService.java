package com.wang.javatools.base;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;

import com.wang.javatools.manager.AppManager;
import com.wang.javatools.widget.toast.ToastUtils;

public class MyService extends Service {
    public MyService() {
    }



    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * 监听横竖屏切换
     * @param newConfig Configuration
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            AppManager.getInstance().setOrientation(Configuration.ORIENTATION_LANDSCAPE);
            ToastUtils.showShortToast(this, "切换为横屏");
        } else {
            AppManager.getInstance().setOrientation(Configuration.ORIENTATION_PORTRAIT);
            ToastUtils.showShortToast(this, "切换为竖屏");
        }
    }
}