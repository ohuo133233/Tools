package com.wang.tools.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wang.tools.R
import com.wang.tools.button.ButtonActivity
import com.wang.tools.language.LanguageActivity
import com.wang.tools.log.LogActivity
import com.wang.tools.main.RecyclerViewAdapter.OnClickListener
import com.wang.tools.net.NetActivity
import com.wang.tools.net.NetStateChangeActivity
import com.wang.tools.other.OtherActivity
import com.wang.tools.permission.PermissionActivity
import com.wang.tools.widget.dialog.DiaLogActivity
import com.wang.tools.widget.popupwindow.PopupWindowActivity
import com.wang.tools.widget.recyclerview.RecyclerViewActivity
import com.wang.tools.widget.suspension.SuspensionActivity
import com.wang.tools.widget.viewpager.ViewPager2Activity
import com.wang.tools.wifi.WIFIActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var list = listOf(
            "动态权限",
            "Log工具",
            "监听网络变化",
            "网络请求",
            "DiaLog演示",
            "recyclerView演示",
            "其他工具演示",
            "WIFI",
            "ViewPager2",
            "Room数据库",
            "中英文语言切换",
            "相机",
            "popupWindow",
            "Suspension",
            "Button",
        )
        val adapter = RecyclerViewAdapter(list, this)


        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        adapter.setonClickListener(object : OnClickListener {
            override fun onClick(view: View, position: Int) {
                when (position) {
                    0 -> startPermissionActivity()
                    1 -> startLogActivity()
                    2 -> startNetStateChangeActivity()
                    3 -> startNetActivity()
                    4 -> startDiaLogActivity()
                    5 -> startRecyclerViewActivity()
                    6 -> startOtherActivity()
                    7 -> startWIFIActivity()
                    8 -> startViewPagerActivity()
                    9 -> startRoomDemoActivity()
                    10 -> startLanguageActivity()
                    11 -> startCameraActivity()
                    12 -> startPopupWindow()
                    13 -> startSuspensionActivity()
                    14 -> startButtonActivity()
                }
            }
        })
    }

    private fun startButtonActivity() {
        val intent = Intent(this, ButtonActivity::class.java)
        startActivity(intent)
    }

    private fun startSuspensionActivity() {
        val intent = Intent(this, SuspensionActivity::class.java)
        startActivity(intent)
    }

    private fun startPopupWindow() {
        val intent = Intent(this, PopupWindowActivity::class.java)
        startActivity(intent)
    }

    private fun startCameraActivity() {
//        val intent = Intent(this, CameraActivity::class.java)
//        startActivity(intent)
    }

    private fun startLanguageActivity() {
        val intent = Intent(this, LanguageActivity::class.java)
        startActivity(intent)
    }

    private fun startWIFIActivity() {
        val intent = Intent(this, WIFIActivity::class.java)
        startActivity(intent)
    }

    private fun startRecyclerViewActivity() {
        val intent = Intent(this, RecyclerViewActivity::class.java)
        startActivity(intent)
    }

    fun startPermissionActivity() {
        val intent = Intent(this, PermissionActivity::class.java)
        startActivity(intent)
    }

    fun startLogActivity() {
        val intent = Intent(this, LogActivity::class.java)
        startActivity(intent)
    }

    fun startNetStateChangeActivity() {
        val intent = Intent(this, NetStateChangeActivity::class.java)
        startActivity(intent)
    }

    fun startNetActivity() {
        val intent = Intent(this, NetActivity::class.java)
        startActivity(intent)
    }

    fun startDiaLogActivity() {
        val intent = Intent(this, DiaLogActivity::class.java)
        startActivity(intent)
    }

    fun startOtherActivity() {
        val intent = Intent(this, OtherActivity::class.java)
        startActivity(intent)
    }

    private fun startViewPagerActivity() {
        val intent = Intent(this, ViewPager2Activity::class.java)
        startActivity(intent)
    }

    private fun startRoomDemoActivity() {
//        val intent = Intent(this, RoomDemoActivity::class.java)
//        startActivity(intent)
    }


}