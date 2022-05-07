package com.wang.javatools.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wang.javatools.manager.AppManager;
import com.wang.javatools.manager.LocalManage;

public class BaseActivity extends AppCompatActivity implements ScreenOrientation {
    protected String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO 修改Log打印行数为子类
        Log.d(TAG, " onCreate");
        AppManager.getInstance().setScreenOrientation(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // TODO 修改Log打印行数为子类
        Log.d(TAG, " onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        // TODO 修改Log打印行数为子类
        Log.d(TAG, " onStart");
    }
    @Override
    protected void onPause() {
        super.onPause();
        // TODO 修改Log打印行数为子类
        Log.d(TAG, " onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        // TODO 修改Log打印行数为子类
        Log.d(TAG, " onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // TODO 修改Log打印行数为子类
        Log.d(TAG, " onDestroy");
        AppManager.getInstance().setScreenOrientation(null);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        // TODO 修改Log打印行数为子类
        Log.d(TAG, " onRestart");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // TODO 修改Log打印行数为子类
        Log.d(TAG, " onActivityResult");
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