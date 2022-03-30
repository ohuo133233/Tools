package com.wang.javatools.utils;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.File;

public class PathUtils {

    public File getInternalStorageFilesDir(Context context) {
        return context.getFilesDir();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public File getInternalStorageDataDir(Context context) {
        return context.getDataDir();
    }


}
