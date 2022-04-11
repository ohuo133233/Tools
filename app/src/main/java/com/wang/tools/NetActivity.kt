package com.wang.tools

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.wang.javatools.net.request.OkHttpManager
import com.wang.javatools.widget.dialog.CommonDialog

class NetActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mDialog: CommonDialog
    private lateinit var mOkHttpManager: OkHttpManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_net)
        initNet()
        initView()
    }

    private fun initNet() {
        mOkHttpManager = OkHttpManager.getInstance()
    }

    private fun initView() {
        val test = findViewById<Button>(R.id.test)
        val sendGet = findViewById<Button>(R.id.send_get)

        test.setOnClickListener(this)
        sendGet.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v == null) {
            return
        }
        when (v.id) {
            R.id.test -> test()
            R.id.send_get -> sendGet()
        }
    }

    private fun sendGet() {
        mDialog = CommonDialog.Build(this)
            .setWidth(ViewGroup.LayoutParams.WRAP_CONTENT)
            .setHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
            .setLayout(R.layout.net_dia_log)
            .setCanceledOnTouchOutside(true)
            .build()

        mDialog.show()
        mDialog.findViewById<Button>(R.id.determine).setOnClickListener {
            mOkHttpManager.get(mDialog.findViewById<EditText>(R.id.get_url).text.toString().trim())
            mDialog.dismiss()
        }
    }

    private fun test() {
        mOkHttpManager.test()
    }
}