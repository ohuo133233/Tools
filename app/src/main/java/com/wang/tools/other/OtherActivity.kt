package com.wang.tools.other

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.wang.javatools.base.BaseActivity
import com.wang.javatools.manager.AppManager
import com.wang.javatools.utils.TimerUtils
import com.wang.javatools.widget.toast.ToastUtils
import com.wang.tools.R

class OtherActivity : BaseActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)

        val getCurrentTimer = findViewById<Button>(R.id.get_current_timer)
        val getOrientation = findViewById<Button>(R.id.get_orientation)

        getCurrentTimer.setOnClickListener(this)
        getOrientation.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if (v == null) {
            return
        }
        when (v.id) {
            R.id.get_current_timer -> getCurrentTimer()
            R.id.get_orientation -> getOrientation()
        }
    }

    private fun getOrientation() {
        if (AppManager.getInstance().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ToastUtils.showShortToast(this, "横屏")
        } else {
            ToastUtils.showShortToast(this, "竖屏")
        }
    }

    private fun getCurrentTimer() {
        val currentTimer = TimerUtils.getInstance().currentTimer
        ToastUtils.showToast(this, "当前系统时间： $currentTimer")
    }


}