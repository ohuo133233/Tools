package com.wang.tools.log

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.wang.javatools.log.LogUtils
import com.wang.javatools.log.Logger
import com.wang.javatools.permission.PermissionManager
import com.wang.tools.R

class LogActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var permissionManager: PermissionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)
        initView()
    }

    private fun initView() {
        val vLog = findViewById<Button>(R.id.v_log)
        val bLog = findViewById<Button>(R.id.b_log)
        val iLog = findViewById<Button>(R.id.i_log)
        val wLog = findViewById<Button>(R.id.w_log)
        val eLog = findViewById<Button>(R.id.e_log)
        val startPrintln = findViewById<Button>(R.id.start_println)
        val colesPrintln = findViewById<Button>(R.id.coles_println)
        val logSaveEnabled = findViewById<Button>(R.id.log_save_enabled)
        val logSaveColes = findViewById<Button>(R.id.log_save_coles)

        vLog.setOnClickListener(this)
        bLog.setOnClickListener(this)
        iLog.setOnClickListener(this)
        wLog.setOnClickListener(this)
        eLog.setOnClickListener(this)
        startPrintln.setOnClickListener(this)
        colesPrintln.setOnClickListener(this)
        logSaveEnabled.setOnClickListener(this)
        logSaveColes.setOnClickListener(this)

        Logger.w("这是一条log日志，测试测试测试 这是一条log日志，测试测试测试" + 1)
        Logger.w("这是一条log日志，测试测试测试 这是一条log日志，测试测试测试" + 2)
        Logger.w("这是一条log日志，测试测试测试 这是一条log日志，测试测试测试" + 3)
        Logger.w("这是一条log日志，测试测试测试 这是一条log日志，测试测试测试" + 4)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.v_log -> LogUtils.v("测试v级log")
            R.id.b_log -> LogUtils.d("测试d级log")
            R.id.i_log -> LogUtils.i("测试i级log")
            R.id.w_log -> LogUtils.w("测试w级log")
            R.id.e_log -> LogUtils.e("测试e级log")
            R.id.start_println -> LogUtils.setIsPrintln(true)
            R.id.coles_println -> LogUtils.setIsPrintln(false)
            R.id.log_save_enabled -> saveLog(true)
            R.id.log_save_coles -> saveLog(false)
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun saveLog(isSave: Boolean) {
        permissionManager = PermissionManager(this)
        permissionManager.requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, null)
        LogUtils.setAutoSave(isSave, this)
    }

    override fun onDestroy() {
        super.onDestroy()
        permissionManager.onDestroy()
    }
}