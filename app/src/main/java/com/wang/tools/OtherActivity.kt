package com.wang.tools

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.wang.javatools.utils.TimerUtils
import com.wang.javatools.widget.toast.ToastUtils

class OtherActivity : AppCompatActivity(), View.OnClickListener {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)

        val getCurrentTimer = findViewById<Button>(R.id.get_current_timer)

        getCurrentTimer.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if (v == null) {
            return
        }
        when (v.id) {
            R.id.get_current_timer -> getCurrentTimer()
        }
    }

    private fun getCurrentTimer() {

        val currentTimer = TimerUtils.getInstance().currentTimer
        ToastUtils.showToast(this, "当前系统时间： $currentTimer")
    }


}