package com.wang.javatools.widget.toast;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {


    /**
     * 快速弹出的的Toast
     *
     * @param context 上下文
     * @param message 信息
     */
    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 快速弹出的的Toast,LENGTH_SHORT的Toast
     *
     * @param context 上下文
     * @param message 信息
     */
    public static void showShortToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 快速弹出的的Toast，LENGTH_LONG的Toast
     *
     * @param context 上下文
     * @param message 信息
     */

    public static void showLongToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

}
