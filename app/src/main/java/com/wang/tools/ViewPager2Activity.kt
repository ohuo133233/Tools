package com.wang.tools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.wang.javatools.widget.recyclerview.BaseRecyclerViewAdapter

class ViewPager2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager2)

        var list = listOf(
            "测试页面1",
            "测试页面2",
            "测试页面3",
            "测试页面4",
            "测试页面5",
            "测试页面6",
            "测试页面7",
            "测试页面8"
        )

        val viewPager = findViewById<ViewPager2>(R.id.view_pager)
        val adapter = BaseRecyclerViewAdapter.Build<String>()
            .setContext(this)
            .setDataList(list)
            .setLayoutId(R.layout.view_page_item)
            .build()


        adapter.setBaseRecyclerViewAdapterBackCall { holder, position ->
            holder.getView<TextView>(R.id.text_view).text = list[position]
        }

        viewPager.adapter = adapter;
    }
}