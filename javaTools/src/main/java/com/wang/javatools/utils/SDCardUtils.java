package com.wang.javatools.utils;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.File;

public class SDCardUtils {

    private static File[] mFiles;
    private static final String TAG = SDCardUtils.class.getSimpleName();

    /**
     * 返回外部储存的读写状态
     *
     * @return true：可用；false：不可用
     */

    public static boolean isExternalStorageWritable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }


    /**
     * 返回所有外部储存的地址
     *
     * @return mFiles 外部储存的
     */
    public static File[] getExternalFilesDirs(Context context) {
        mFiles = context.getExternalFilesDirs(Environment.MEDIA_MOUNTED);
        for (File file : mFiles) {
            Log.d(TAG, "外部储存地:" + file.getAbsolutePath());
        }
        return mFiles;
    }

}
