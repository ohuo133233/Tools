package com.wang.javatools.log;

import static android.os.Process.myPid;

import android.content.Context;
import android.util.Log;

import com.wang.javatools.utils.FileUtil;
import com.wang.javatools.utils.TimerUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * TODO
 * 1.区分整机log和app log分别保存
 * 2.定时清理和保存时机
 * 3.E级log写为红色
 */
public class LogConfigure {
    private static final String TAG = "LogConfigure";
    private File mLogFile;
    private Process mProcess;

    public void initPath(Context context) {
        File externalCacheDir = context.getExternalCacheDir();
        Log.d(TAG, "externalCacheDir: " + externalCacheDir.getAbsolutePath());
        mLogFile = new File(externalCacheDir + File.separator + TimerUtils.getInstance().getCurrentTimer() + "_app_log.txt");
        FileUtil.createFile(mLogFile);
    }

    public void getLog() {
        String cmd = "logcat *:v | grep \"(" + myPid() + ")\"";
        try {
            mProcess = Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread() {
            @Override
            public void run() {
                super.run();
                InputStream inputStream = mProcess.getInputStream();
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(mLogFile.getAbsoluteFile());
                    byte[] b = new byte[1024];
                    while ((inputStream.read(b)) != -1) {
                        // 写入数据
                        fos.write(b);
                    }
                    inputStream.close();
                    fos.close();
                    Log.e(TAG, "log写入成功");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }


}
