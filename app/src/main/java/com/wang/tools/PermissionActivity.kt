package com.wang.tools

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.wang.javatools.manager.permission.IPermissionCallBack
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
            R.id.camera -> camera()
            R.id.accept_handover -> callPhone()
            R.id.send_sms -> sendSMS()
            R.id.write_external_storage -> writeExternalStorage()
        }
    }

    private fun camera() {
        permissionManager.requestPermission(Manifest.permission.CAMERA,
            object : IPermissionCallBack {
                override fun success() {
                    Log.e("TAG", "success")
                }

                override fun fail() {
                    Log.e("TAG", "fail")
                }

                override fun noMoreReminders() {
                    Log.e("TAG", "noMoreReminders")
                }

            })
    }

    private fun callPhone() {
        permissionManager.requestPermission(Manifest.permission.ACCEPT_HANDOVER,
            object : IPermissionCallBack {
                override fun success() {
                    TODO("Not yet implemented")
                }

                override fun fail() {
                    TODO("Not yet implemented")
                }

                override fun noMoreReminders() {
                    TODO("Not yet implemented")
                }
            })
    }

    private fun sendSMS() {
        permissionManager.requestPermission(Manifest.permission.SEND_SMS,
            object : IPermissionCallBack {
                override fun success() {
                    TODO("Not yet implemented")
                }

                override fun fail() {
                    TODO("Not yet implemented")
                }

                override fun noMoreReminders() {
                    TODO("Not yet implemented")
                }
            })
    }

    private fun writeExternalStorage() {
        permissionManager.requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,
            object : IPermissionCallBack {
                override fun success() {
                    TODO("Not yet implemented")
                }

                override fun fail() {
                    TODO("Not yet implemented")
                }

                override fun noMoreReminders() {
                    TODO("Not yet implemented")
                }
            })
    }
}