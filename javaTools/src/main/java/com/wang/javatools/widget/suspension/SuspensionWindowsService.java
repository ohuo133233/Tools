package com.wang.javatools.widget.suspension;

import android.accessibilityservice.AccessibilityService;
import android.graphics.PixelFormat;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

import com.wang.javatools.R;
import com.wang.javatools.utils.IPUtils;

public class SuspensionWindowsService extends AccessibilityService implements LifecycleEventObserver {
    private boolean mIsShowWindow;
    private WindowManager windowManager;
    private View floatRootView;
    private View closeFloatRootView;

    private static String IP;


    public static void setIP(String IP) {
        SuspensionWindowsService.IP = IP;
    }

    public void onCreate() {
        super.onCreate();
        if (mIsShowWindow) {
            windowManager.removeView(floatRootView);
            mIsShowWindow = false;
        } else {
            showWindow();
        }

    }


    private void showWindow() {

        mIsShowWindow = true;

        // 设置LayoutParam
        // 获取WindowManager服务
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(outMetrics);

        WindowManager.LayoutParams layoutParam = new WindowManager.LayoutParams();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            layoutParam.type = WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY;
            // 刘海屏延伸到刘海里面
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                layoutParam.layoutInDisplayCutoutMode =
                        WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            }
        } else {
            layoutParam.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }

        layoutParam.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        layoutParam.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParam.height = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParam.format = PixelFormat.TRANSPARENT;


        floatRootView = LayoutInflater.from(this).inflate(R.layout.activity_float_item, null);
//        closeFloatRootView = LayoutInflater.from(this).inflate(R.layout.colse_float, null);
//        floatRootView.setOnTouchListener(new ItemViewTouchListener(layoutParam, windowManager, null));
        windowManager.addView(floatRootView, layoutParam);


        //  initView();
    }

    private void initView() {
        Button ping_ip = floatRootView.findViewById(R.id.ping_ip);
        ping_ip.setOnClickListener(v -> {
            IPUtils ipUtils = new IPUtils();
            ipUtils.pingIP(IP);
        });

        ImageButton min_and_max = floatRootView.findViewById(R.id.min_and_max);
        min_and_max.setOnClickListener(v -> {
            windowManager.removeView(floatRootView);
            closeLayout();
        });

        closeFloatRootView.setOnLongClickListener(v1 -> {
            windowManager.removeView(closeFloatRootView);
            showOpenLayout();
            return true;
        });

    }

    public void showOpenLayout() {
        WindowManager.LayoutParams layoutParam = new WindowManager.LayoutParams();
        show(layoutParam);
        layoutParam.height = 500;
        layoutParam.width = 500;
        layoutParam.format = PixelFormat.RGBA_8888;

        floatRootView.setOnTouchListener(new ItemViewTouchListener(layoutParam, windowManager, null));
        windowManager.addView(floatRootView, layoutParam);
    }

    // 适配Android悬浮窗权限
    public void show(WindowManager.LayoutParams layoutParam) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            layoutParam.type = WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY;
            //刘海屏延伸到刘海里面
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                layoutParam.layoutInDisplayCutoutMode =
                        WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            }
        } else {
            layoutParam.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }


    }

    public void closeLayout() {
        WindowManager.LayoutParams layoutParam = new WindowManager.LayoutParams();
        layoutParam.height = 16 * 10;
        layoutParam.width = 16 * 10;
        show(layoutParam);
        closeFloatRootView.setOnTouchListener(new ItemViewTouchListener(layoutParam, windowManager, new SuspensionOnClickListener() {
            @Override
            public void setOnLongClickListener() {
                windowManager.removeView(closeFloatRootView);
                showOpenLayout();
            }
        }));
        windowManager.addView(closeFloatRootView, layoutParam);
    }

    @Override
    public void onDestroy() {
        windowManager.removeView(floatRootView);
        super.onDestroy();
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

    }

    @Override
    public void onInterrupt() {

    }

    @Override
    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {

    }
}