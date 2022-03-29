package com.wang.tools

import android.Manifest
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.wang.javatools.manager.permission.PermissionManager


class PermissionActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var permissionManager: PermissionManager;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)
        initView()
        permissionManager = PermissionManager(this)

    }

    private fun initView() {
        val camera = findViewById<Button>(R.id.camera)
        val acceptHandover = findViewById<Button>(R.id.accept_handover)
        val sendSMS = findViewById<Button>(R.id.send_sms)
        val writeExternalStorage = findViewById<Button>(R.id.write_external_storage)

        camera.setOnClickListener(this)
        acceptHandover.setOnClickListener(this)
        sendSMS.setOnClickListener(this)
        sendSMS.setOnClickListener(this)
        writeExternalStorage.setOnClickListener(this)

    }


    override fun onClick(v: View?) {
        if (v == null) {
            return
        }
        when (v.id) {
            R.id.camera -> permissionManager.requestPermission(Manifest.permission.CAMERA)
            R.id.accept_handover -> permissionManager.requestPermission(Manifest.permission.ACCEPT_HANDOVER)
            R.id.send_sms -> permissionManager.requestPermission(Manifest.permission.SEND_SMS)
            R.id.write_external_storage -> permissionManager.requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
    }
}