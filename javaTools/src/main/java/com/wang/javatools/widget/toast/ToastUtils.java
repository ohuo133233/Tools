package com.wang.javatools.widget.toast;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

import com.wang.javatools.base.LifecycleObserver;

/**
 * 这个类只是封装Toast的方法，只为快速调用。无自定义
 */
public class ToastUtils implements LifecycleObserver {


    /**
     * 快速弹出的的Toast
     *
     * @param context 上下文
     * @param message 信息
     */
    public static void showToast(@NonNull Context context, @NonNull String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 快速弹出的的Toast,LENGTH_SHORT的Toast
     *
     * @param context 上下文
     * @param message 信息
     */
    public static void showShortToast(@NonNull Context context, @NonNull String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 快速弹出的的Toast，LENGTH_LONG的Toast
     *
     * @param context 上下文
     * @param message 信息
     */

    public static void showLongToast(@NonNull Context context, @NonNull String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {

    }
}
