package com.wang.javatools.base;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.wang.javatools.manager.AppManager;
import com.wang.javatools.manager.LocalManage;

public class BaseActivity extends AppCompatActivity implements ScreenOrientation {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getInstance().setScreenOrientation(this);
    }

    /**
     * 重写此方法，当横竖屏变化会收到横屏的回调，无论是否开启横屏锁定
     */
    @Override
    public void landscape() {

    }

    /**
     * 重写此方法，当横竖屏变化会收到竖屏的回调，无论是否开启横屏锁定
     */
    @Override
    public void portrait() {

    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(LocalManage.getInstance().setLocal(context));
    }
}