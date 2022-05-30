package com.wang.javatools.utils;

import android.util.Log;

import java.io.IOException;

public class IPUtils {
    private static final String TAG = IPUtils.class.getName();

    public void pingIP(final String ipString) {
        Log.d(TAG, "IP: " + ipString);
        final Thread pingThread = new Thread() {
            @Override
            public void run() {

                // 代表ping 3 次 超时时间为10秒
                // ping3次
                Process p;
                try {
                    p = Runtime.getRuntime().exec("ping -c 3 -w 10 " + ipString);
                    int status = p.waitFor();
                    Log.d("TAG", "status: " + status);
                    if (status == 0) {
                        //代表成功
                        Log.d("TAG", "成功");

//                        Toast.makeText(mActivity, "成功", Toast.LENGTH_SHORT).show();
                    } else {
                        //代表失败
                        Log.d("TAG", "失败");
//                        Toast.makeText(mActivity, "失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        pingThread.start();
    }
}
