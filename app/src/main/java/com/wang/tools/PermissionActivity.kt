package com.wang.tools

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.wang.javatools.permission.IPermissionCallBack
import com.wang.javatools.permission.PermissionManager
import com.wang.javatools.utils.ToastUtils


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
                    showPermissionSuccess()
                }

                override fun fail() {
                    showPermissionFail()
                }

                override fun noMoreReminders() {
                    showPermissionNoMoreReminders()
                }

                override fun alreadyObtainedPermission() {
                    showPermissionAlreadyObtained()
                }

            })
    }

    private fun callPhone() {
        permissionManager.requestPermission(Manifest.permission.ACCEPT_HANDOVER,
            object : IPermissionCallBack {
                override fun success() {
                    showPermissionSuccess()
                }

                override fun fail() {
                    showPermissionFail()
                }

                override fun noMoreReminders() {
                    showPermissionNoMoreReminders()
                }

                override fun alreadyObtainedPermission() {
                    showPermissionAlreadyObtained()
                }
            })
    }

    private fun sendSMS() {
        permissionManager.requestPermission(Manifest.permission.SEND_SMS,
            object : IPermissionCallBack {
                override fun success() {
                    showPermissionSuccess()
                }

                override fun fail() {
                    showPermissionFail()
                }

                override fun noMoreReminders() {
                    showPermissionNoMoreReminders()
                }

                override fun alreadyObtainedPermission() {
                    showPermissionAlreadyObtained()
                }
            })
    }

    private fun writeExternalStorage() {
        permissionManager.requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,
            object : IPermissionCallBack {
                override fun success() {
                    showPermissionSuccess()
                }

                override fun fail() {
                    showPermissionFail()
                }

                override fun noMoreReminders() {
                    showPermissionNoMoreReminders()
                }

                override fun alreadyObtainedPermission() {
                    showPermissionAlreadyObtained()
                }
            })
    }

    private fun showPermissionAlreadyObtained() {
        runOnUiThread {
            ToastUtils.showToast(this, "已经有权限")
        }
    }

    private fun showPermissionSuccess() {
        runOnUiThread {
            ToastUtils.showToast(this, "成功")
        }
    }

    private fun showPermissionFail() {
        runOnUiThread {
            ToastUtils.showToast(this, "失败")
        }
    }

    private fun showPermissionNoMoreReminders() {
        runOnUiThread {
            ToastUtils.showToast(this, "用户点击了不再提醒")
        }
    }
}