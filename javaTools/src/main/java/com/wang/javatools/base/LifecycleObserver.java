package com.wang.javatools.base;

import android.util.Log;

import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

public interface LifecycleObserver extends DefaultLifecycleObserver {

    String TAG = "LifecycleObserver";

    @Override
    default void onCreate(LifecycleOwner owner) {
        Log.v(TAG, "onCreate");
    }

    @Override
    default void onStart(LifecycleOwner owner) {
        Log.v(TAG, "onStart");
    }

    @Override
    default void onResume(LifecycleOwner owner) {
        Log.v(TAG, "onResume");
    }

    @Override
    default void onPause(LifecycleOwner owner) {
        Log.v(TAG, "onPause");
    }

    @Override
    default void onDestroy(LifecycleOwner owner) {
        Log.v(TAG, "onDestroy");
    }
}
