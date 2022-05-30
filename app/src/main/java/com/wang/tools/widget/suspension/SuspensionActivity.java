package com.wang.tools.widget.suspension;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.wang.javatools.widget.suspension.SuspensionWindowsService;
import com.wang.tools.R;

import java.util.ArrayList;

public class SuspensionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suspension);
        checkAccessibilityPermission();

    }


    public void checkAccessibilityPermission() {
        if (isServiceRunning(this, SuspensionWindowsService.class.getName())) {
            SuspensionWindowsService.setIP("127.0.0.1");
            Intent intent = new Intent(getApplicationContext(), SuspensionWindowsService.class);
            startService(intent);
        } else {
            accessibilityToSettingPage();
        }
    }


    /**
     * 判断Service是否开启
     */
    private boolean isServiceRunning(Context context, String ServiceName) {
        if (TextUtils.isEmpty(ServiceName)) {
            return false;
        }
        ActivityManager myManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ArrayList<ActivityManager.RunningServiceInfo> runningService = (ArrayList<ActivityManager.RunningServiceInfo>) myManager.getRunningServices(1000);
        for (int i = 0; i < runningService.size(); i++) {
            if (runningService.get(i).service.getClassName().equals(ServiceName)) {
                return true;
            }
        }

        return false;
    }


    /**
     * 跳转到设置页面申请打开无障碍辅助功能
     */
    // 开启辅助功能页面
    private void accessibilityToSettingPage() {

        try {
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (Exception exception) {
            Intent intent = new Intent(Settings.ACTION_SETTINGS);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            exception.printStackTrace();
        }
    }

}