package com.wang.tools.dialog

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.wang.javatools.widget.dialog.CommonDialog
import com.wang.javatools.widget.recyclerview.BaseRecyclerViewAdapter
import com.wang.tools.R


class DiaLogActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dia_log)
        val standardDialog = findViewById<Button>(R.id.standard_dialog)
        val fragmentDialog = findViewById<Button>(R.id.fragment_dialog)

        standardDialog.setOnClickListener(this)
        fragmentDialog.setOnClickListener(this)
    }

    private fun standardDialog() {
        val dialog = CommonDialog.Build()
            .setContext(this)
            .setWidth(ViewGroup.LayoutParams.WRAP_CONTENT)
            .setHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
            .setLayout(R.layout.common_dia_log)
            .setText(R.id.tile, "自定义标题")
            .setText(R.id.message, "自定义内容")
            .setText(R.id.left_button, "确定")
            .setText(R.id.right_button, "取消")
            .setCanceledOnTouchOutside(true)
            .build()
        dialog.show()
    }

    private fun fragmentDialog() {
        val dialog = CommonDialog.Build()
            .setContext(this)
            .setWidth(ViewGroup.LayoutParams.MATCH_PARENT)
            .setHeight(ViewGroup.LayoutParams.MATCH_PARENT)
            .setLayout(R.layout.fragment_dia_log)
            .setCanceledOnTouchOutside(true)
            .build()

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

        val viewPager = dialog.findViewById<ViewPager2>(R.id.view_pager)
        val adapter = BaseRecyclerViewAdapter.Build<String>()
            .setContext(this)
            .setDataList(list)
            .setLayoutId(R.layout.view_page_item)
            .build()

        adapter.setBaseRecyclerViewAdapterBackCall { holder, position ->
            holder.getView<Button>(R.id.coles).setOnClickListener {
                dialog.dismiss()
            }
            holder.getView<TextView>(R.id.text_view).text = list[position]
        }

        viewPager.adapter = adapter;
        dialog.show()
    }


    override fun onClick(v: View?) {
        if (v == null) {
            return
        }
        when (v.id) {
            R.id.standard_dialog -> standardDialog()
            R.id.fragment_dialog -> fragmentDialog()
        }
    }
}