package com.wang.tools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.wang.javatools.utils.TimerUtils
import com.wang.javatools.utils.ToastUtils

class OtherActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)

        val getCurrentTimer = findViewById<Button>(R.id.get_current_timer)


        getCurrentTimer.setOnClickListener(this)
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
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