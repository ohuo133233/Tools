package com.wang.javatools.log;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.wang.javatools.R;
import com.wang.javatools.utils.TimerUtils;

public class LogService extends Service {

    private static final String TAG = LogService.class.getSimpleName();
    private static final String CHANNEL_ONE_ID = "com.wang.tools";
    private static final String CHANNEL_ONE_NAME = "Channel One";
    private NotificationChannel mNotificationChannel;
    private Notification mNotification;
    private LogConfigure mLogConfigure;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        initNotificationChannel();
        initNotification();
        // 参数一：唯一的通知标识；参数二：通知消息。
        // 开始前台服务
        startForeground(110, mNotification);
        initLogConfigure();
        autoSave();

        return super.onStartCommand(intent, flags, startId);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initNotification() {
        // 在API16之后，可以使用build()来进行Notification的构建 Notification
        mNotification = new Notification.Builder(this)
                .setContentTitle("hi")
                .setContentText("正在自动收集Log")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setWhen(System.currentTimeMillis())
                .setChannelId(CHANNEL_ONE_ID)
                .build();
    }

    private void initNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            mNotificationChannel = new NotificationChannel(CHANNEL_ONE_ID,
                    CHANNEL_ONE_NAME, NotificationManager.IMPORTANCE_HIGH);
            mNotificationChannel.enableLights(true);
            mNotificationChannel.setLightColor(Color.RED);
            mNotificationChannel.setShowBadge(true);
            mNotificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.createNotificationChannel(mNotificationChannel);
        }
    }

    private void initLogConfigure() {
        mLogConfigure = new LogConfigure();
        mLogConfigure.initPath(this);
    }

    private void autoSave() {
        TimerUtils.getInstance().timer(1000 * 60, () -> {
            Log.d(TAG, "自动保存log");
            mLogConfigure.createLog();
            mLogConfigure.getMyAppLogCat();
            mLogConfigure.writeLogFile();
        });

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return null;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy()");
        // 停止前台服务--参数：表示是否移除之前的通知super.onDestroy();
        stopForeground(true);
    }
}
