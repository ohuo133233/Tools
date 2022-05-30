package com.wang.javatools.widget.suspension;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;


public class ItemViewTouchListener implements View.OnTouchListener {
    private WindowManager.LayoutParams wl;
    private WindowManager windowManager;
    private SuspensionOnClickListener mSuspensionOnClickListener;
    private int x = 0;
    private int y = 0;


    private long startTimer;
    private long endTimer;

    public ItemViewTouchListener(WindowManager.LayoutParams wl, WindowManager windowManager, SuspensionOnClickListener suspensionOnClickListener) {
        this.wl = wl;
        this.windowManager = windowManager;
        this.mSuspensionOnClickListener = suspensionOnClickListener;
    }


    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = (int) motionEvent.getRawX();
                y = (int) motionEvent.getRawY();

                startTimer = System.currentTimeMillis();
                Log.e("TAG", "startTimer: " + startTimer);
                break;
            case MotionEvent.ACTION_MOVE:
                int nowX = (int) motionEvent.getRawX();
                int nowY = (int) motionEvent.getRawY();
                int movedX = nowX - x;
                int movedY = nowY - y;
                x = nowX;
                y = nowY;

                wl.x += movedX;
                wl.y += movedY;
                break;
            case MotionEvent.ACTION_UP:
                endTimer = System.currentTimeMillis();
                Log.e("TAG", "endTimer: " + endTimer);
                float timer = endTimer - startTimer;
                Log.e("TAG", "timer: " + timer);
                if (timer >= 3000) {
                    Log.e("TAG", "长按大于3000");
                    if (mSuspensionOnClickListener != null) {
                        mSuspensionOnClickListener.setOnLongClickListener();
                    }
                } else {
                    Log.e("TAG", "不长按大于3000");
                }
                break;
        }
        // 更新悬浮球控件位置
        windowManager.updateViewLayout(view, wl);
        return true;


    }

}
