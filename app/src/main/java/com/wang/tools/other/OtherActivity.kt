package com.wang.tools.other

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.wang.javatools.base.BaseActivity
import com.wang.javatools.manager.AppManager
import com.wang.javatools.manager.LocalManage
import com.wang.javatools.utils.TimerUtils
import com.wang.javatools.widget.dialog.CommonDialog
import com.wang.javatools.widget.toast.ToastUtils
import com.wang.tools.R

class OtherActivity : BaseActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)

        val getCurrentTimer = findViewById<Button>(R.id.get_current_timer)
        val getOrientation = findViewById<Button>(R.id.get_orientation)
        val switchLanguage = findViewById<Button>(R.id.switch_language)

        getCurrentTimer.setOnClickListener(this)
        getOrientation.setOnClickListener(this)
        switchLanguage.setOnClickListener(this)

    }

    override fun landscape() {
        super.landscape()
        ToastUtils.showShortToast(this, "切换为横屏")

    }

    override fun portrait() {
        super.portrait()
        ToastUtils.showShortToast(this, "切换为竖屏")
    }

    override fun onClick(v: View?) {
        if (v == null) {
            return
        }
        when (v.id) {
            R.id.get_current_timer -> getCurrentTimer()
            R.id.get_orientation -> getOrientation()
            R.id.switch_language -> switchLanguage()
        }
    }

    private fun switchLanguage() {
        val commonDialog = CommonDialog.Build(this)
            .setLayout(R.layout.select_dialog)
            .setCanceledOnTouchOutside(true)
            .setHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
            .setWidth(ViewGroup.LayoutParams.WRAP_CONTENT)
            .setText(R.id.select_one, "中文")
            .setText(R.id.select_two, "英文")
            .setText(R.id.select_three, "跟随系统")
            .build()

        commonDialog.findViewById<Button>(R.id.select_one)
            .setOnClickListener {
                LocalManage.getInstance().setChina()
                commonDialog.dismiss()
                finish()
            }

        commonDialog.findViewById<Button>(R.id.select_two)
            .setOnClickListener {
                LocalManage.getInstance().setEnglish()
                commonDialog.dismiss()
                finish()
            }
        commonDialog.show()


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