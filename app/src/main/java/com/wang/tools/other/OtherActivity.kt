package com.wang.tools.other

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.wang.javatools.utils.TimerUtils
import com.wang.javatools.widget.toast.ToastUtils
import com.wang.tools.R

class OtherActivity : AppCompatActivity(), View.OnClickListener {


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
        }
    }

    private fun getCurrentTimer() {
        val currentTimer = TimerUtils.getInstance().currentTimer
        ToastUtils.showToast(this, "当前系统时间： $currentTimer")
    }


}