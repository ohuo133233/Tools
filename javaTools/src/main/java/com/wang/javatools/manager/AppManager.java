package com.wang.javatools.manager;

import android.app.ActivityManager;
import android.app.Application;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.text.TextUtils;

import com.tencent.mmkv.MMKV;
import com.wang.javatools.base.BaseConstant;
import com.wang.javatools.utils.TimerUtils;

import java.util.List;

// TODO 第一次启动方法待完善
public class AppManager {

    private AppManager() {

    }

    public static AppManager getInstance() {
        return AppManagerHolder.instance;
    }


    private static class AppManagerHolder {
        private final static AppManager instance = new AppManager();
    }

    /**
     * 返回App是否第一次启动
     */
    public boolean isFirstStart() {
        MMKV mmkv = MMKV.defaultMMKV();
        return mmkv.decodeBool(BaseConstant.IS_FIRST_START);
    }

    /**
     * 设置App第一次启动的标志位
     * 默认不需要调用，会自动调用。
     */
    public void setFirstStart(boolean isFirstStart) {
        MMKV mmkv = MMKV.defaultMMKV();
        mmkv.encode(BaseConstant.IS_FIRST_START, isFirstStart);
    }

    /**
     * 设置App第一次启动的时间
     * 默认不需要调用，会自动调用。
     */
    public void setFirstStartDays() {
        MMKV mmkv = MMKV.defaultMMKV();
        mmkv.encode(BaseConstant.IS_FIRST_DAYS, TimerUtils.getInstance().getCurrentTimer());
    }

    /**
     * 返回App第一次启动到现在的时间
     *
     * @return 时间类型字符串 yyyy-MM-dd HH:mm:ss
     */
    public String getFirstStartDays() {
        MMKV mmkv = MMKV.defaultMMKV();
        return mmkv.decodeString(BaseConstant.IS_FIRST_DAYS);
    }

    /**
     * 返回应用是否在后台
     */
    public boolean isBackstage(Application application) {
        ActivityManager activityManager = (ActivityManager) application.getSystemService(Service.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcessInfoList = activityManager.getRunningAppProcesses();
        if (runningAppProcessInfoList == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo processInfo : runningAppProcessInfoList) {
            if (processInfo.processName.equals(application.getPackageName()) &&
                    processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }

    /**
     * 返回应用是否在前台
     */
    public static boolean isRunningForeground(Application context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        String currentPackageName = cn.getPackageName();
        if (!TextUtils.isEmpty(currentPackageName) && currentPackageName.equals(context.getPackageName())) {
            return true;
        }
        return false;
    }


    /**
     * 设置为Debug模式
     */
    public void setDebug() {

    }


}
