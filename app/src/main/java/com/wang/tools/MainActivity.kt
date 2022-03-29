package com.wang.tools

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wang.tools.RecyclerViewAdapter.OnClickListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var list = listOf("动态权限", "Log工具", "监听网络变化", "网络请求", "DiaLog演示", "其他工具演示")
        val adapter = RecyclerViewAdapter(list, this)


        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        adapter.setonClickListener(object : OnClickListener {
            override fun onClick(view: View, position: Int) {
                when (position) {
                    0 -> startPermissionActivity()
                    1 -> startLogActivity()
                    2 -> print("x == 2")
                    3 -> startNetStateChangeActivity()
                    4 -> startDiaLogActivity()
                }
            }
        })
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
        val intent = Intent(this, LogActivity::class.java)
        startActivity(intent)
    }

    fun startDiaLogActivity() {
        val intent = Intent(this, DiaLogActivity::class.java)
        startActivity(intent)
    }

}