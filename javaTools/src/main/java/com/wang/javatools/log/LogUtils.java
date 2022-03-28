package com.wang.javatools.log;

import android.util.Log;

public class LogUtils {
    private static final String TAG = "LogUtils";
    private static boolean isPrintln = true;
    private static boolean isDebug = true;
    private static boolean isAutoSave = true;

    public void v(String message) {
        if (isPrintln) {
            Log.v(TAG, message);
        }
    }

    public void b(String message) {
        if (isPrintln) {
            Log.d(TAG, message);
        }
    }


    public void i(String message) {
        if (isPrintln) {
            Log.i(TAG, message);
        }
    }

    public void w(String message) {
        if (isPrintln && isDebug) {
            Log.w(TAG, message);
        }
    }

    public void e(String message) {
        if (isPrintln && isDebug) {
            Log.w(TAG, message);
        }
    }

    //------------------功能----------------------------//

    /**
     * 是否打印log
     *
     * @param isPrintln 打印的状态
     */
    public static void setIsPrintln(boolean isPrintln) {
        LogUtils.isPrintln = isPrintln;
    }

    /**
     * 是否debug模式：
     * debug状态：包任何情况都输出所有log，并且全部Log输出（包括其他应用）
     * release状态：只输出E和W级别log，只保存当前的AppE级和W级的日志。
     *
     * @param isDebug
     */
    public static void setIsDebug(boolean isDebug) {
        LogUtils.isDebug = isDebug;
    }

    /**
     * 操作自动保存log功能
     * 默认自动打开
     */
    public void setAutoSave(boolean isAutoSave) {
        LogUtils.isAutoSave = isAutoSave;
    }

    public void setOptimizeFormat(){

    }


}
