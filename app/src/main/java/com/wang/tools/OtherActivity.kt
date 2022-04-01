package com.wang.tools

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.wang.javatools.manager.AppManager
import com.wang.javatools.utils.TimerUtils
import com.wang.javatools.widget.toast.ToastUtils

class OtherActivity : AppCompatActivity(), View.OnClickListener {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)

        val getCurrentTimer = findViewById<Button>(R.id.get_current_timer)
        val isFirstStart = findViewById<Button>(R.id.is_first_start)

        getCurrentTimer.setOnClickListener(this)
        isFirstStart.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if (v == null) {
            return
        }
        when (v.id) {
            R.id.get_current_timer -> getCurrentTimer()
            R.id.is_first_start -> isFirstStart()
        }
    }

    private fun isFirstStart() {
        val firstStart = AppManager.getInstance().isFirstStart
        ToastUtils.showShortToast(this, "是否第一次启动$firstStart")
    }

    private fun getCurrentTimer() {
        val currentTimer = TimerUtils.getInstance().currentTimer
        ToastUtils.showToast(this, "当前系统时间： $currentTimer")
    }


}