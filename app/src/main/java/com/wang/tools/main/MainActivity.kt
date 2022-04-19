package com.wang.tools.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wang.tools.R
import com.wang.tools.dialog.DiaLogActivity
import com.wang.tools.log.LogActivity
import com.wang.tools.main.RecyclerViewAdapter.OnClickListener
import com.wang.tools.net.NetActivity
import com.wang.tools.net.NetStateChangeActivity
import com.wang.tools.other.OtherActivity
import com.wang.tools.permission.PermissionActivity
import com.wang.tools.recyclerview.RecyclerViewActivity
import com.wang.tools.room.RoomDemoActivity
import com.wang.tools.viewpager.ViewPager2Activity
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
                }
            }
        })
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
        val intent = Intent(this, RoomDemoActivity::class.java)
        startActivity(intent)
    }


}