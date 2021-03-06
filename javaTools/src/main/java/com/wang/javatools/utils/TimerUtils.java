package com.wang.javatools.utils;

import android.os.Handler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerUtils {

    private static TimerUtils sTimerUtils = null;
    private Handler mHandler = new Handler();

    private TimerUtils() {
    }

    public static TimerUtils getInstance() {
        if (sTimerUtils == null) {
            synchronized (TimerUtils.class) {
                if (sTimerUtils == null) {
                    sTimerUtils = new TimerUtils();
                }
            }
        }
        return sTimerUtils;
    }

    /**
     * 倒计时
     * 在time执行一次runnable
     *
     * @param time     毫秒
     * @param runnable 回调
     */
    public void countDown(long time, Runnable runnable) {
        mHandler.postDelayed(runnable, time);
    }

    /**
     * 定时器
     * 每隔@time毫秒执行一次runnable
     *
     * @param time     毫秒
     * @param runnable 回调
     */
    public void timer(long time, final Runnable runnable) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runnable.run();
            }
        };
        timer.schedule(task, 0, time);
    }

    public String getCurrentTimer() {
        // 设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // new Date()为获取当前系统时间
        return df.format(new Date());
    }
}
