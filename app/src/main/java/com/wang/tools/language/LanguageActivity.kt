package com.wang.tools.language

import android.os.Bundle
import android.view.View
import android.widget.Button
import com.wang.javatools.base.BaseActivity
import com.wang.javatools.manager.LocalManage
import com.wang.tools.R

class LanguageActivity : BaseActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)

        initView()
    }

    private fun initView() {
        val setChinese = findViewById<Button>(R.id.set_chinese)
        val setEnglish = findViewById<Button>(R.id.set_english)
        val followSystem = findViewById<Button>(R.id.follow_system)

        setChinese.setOnClickListener(this)
        setEnglish.setOnClickListener(this)
        followSystem.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v == null) {
            return
        }

        when (v.id) {
            R.id.set_chinese -> setChinese()
            R.id.set_english -> setEnglish()
            R.id.follow_system -> followSystem()
        }
    }

    private fun followSystem() {

    }

    private fun setEnglish() {
        LocalManage.getInstance().setEnglish()
        finish()
    }

    private fun setChinese() {
        LocalManage.getInstance().setChina()
        finish()
    }
}