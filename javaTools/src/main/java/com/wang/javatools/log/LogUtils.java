package com.wang.javatools.log;

import android.content.Context;
import android.os.Process;
import android.util.Log;

public class LogUtils {
    private static String TAG = "LogUtils";
    private static boolean isPrintln = true;
    private static boolean isDebug;
    private static boolean isAutoSave;


    public static void v(String message) {
        if (isPrintln) {
            Log.v(TAG, message);
        }
    }

    public static void v(String TAG, String message) {
        if (isPrintln) {
            Log.v(TAG, message);
        }
    }

    public static void b(String message) {
        if (isPrintln) {
            Log.d(TAG, message);
        }
    }

    public static void b(String TAG, String message) {
        if (isPrintln) {
            Log.d(TAG, message);
        }
    }


    public static void i(String message) {
        if (isPrintln) {
            Log.i(TAG, message);
        }
    }

    public static void i(String TAG, String message) {
        if (isPrintln) {
            Log.i(TAG, message);
        }
    }

    public static void w(String message) {
        if (isPrintln) {
            Log.w(TAG, message);
        }
    }

    public static void w(String TAG, String message) {
        if (isPrintln) {
            Log.w(TAG, message);
        }
    }

    public static void e(String message) {
        if (isPrintln) {
            Log.e(TAG, message);
        }
    }

    public static void e(String TAG, String message) {
        if (isPrintln) {
            Log.e(TAG, message);
        }
    }

    //------------------功能----------------------------//


    /**
     * 设置没有使用传入TAG字段的方法默认的TAG
     *
     * @param TAG 打印log的TAG
     */
    public static void setTAG(String TAG) {
        LogUtils.TAG = TAG;
    }

    /**
     * 是否打印log，默认全部输出
     *
     * @param isPrintln 打印的状态
     */
    public static void setIsPrintln(boolean isPrintln) {
        LogUtils.isPrintln = isPrintln;
    }

    /**
     * 是否debug模式，默认关闭
     * debug状态：包任何情况都输出所有log，并且全部Log输出（包括其他应用）
     * release状态：只输出E和W级别log，只保存当前的AppE级和W级的日志。
     *
     * @param isDebug debug开关
     */
    public static void setIsDebug(boolean isDebug) {
        LogUtils.isDebug = isDebug;
    }

    /**
     * TODO 未开始
     * 操作自动保存log功能
     * 默认自动打开
     *
     * @param isAutoSave 自动保存log的开关
     * @param context    如果是开启log保存需要传入上下文
     */
    public static void setAutoSave(boolean isAutoSave, Context context) {
        LogUtils.isAutoSave = isAutoSave;

    }

    public void setOptimizeFormat() {

    }


}