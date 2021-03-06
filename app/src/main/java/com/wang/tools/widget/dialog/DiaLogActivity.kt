package com.wang.tools.widget.dialog

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.wang.javatools.widget.dialog.CommonDialog
import com.wang.javatools.widget.recyclerview.CommonRecyclerViewAdapter
import com.wang.tools.R


class DiaLogActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dia_log)

        val standardDialog = findViewById<Button>(R.id.standard_dialog)
        val fragmentDialog = findViewById<Button>(R.id.fragment_dialog)
        val testDialogLifecycle = findViewById<Button>(R.id.test_dialog_lifecycle)
        val animDialog = findViewById<Button>(R.id.anim_dialog)

        standardDialog.setOnClickListener(this)
        fragmentDialog.setOnClickListener(this)
        testDialogLifecycle.setOnClickListener(this)
        animDialog.setOnClickListener(this)


    }

    private fun standardDialog() {
        val dialog = CommonDialog.Build(this)
            .setLayout(R.layout.common_dia_log)
            .setText(R.id.tile, "自定义标题")
            .setText(R.id.message, "自定义内容")
            .setText(R.id.left_button, "确定")
            .setText(R.id.right_button, "取消")
            .setCanceledOnTouchOutside(true)
            .build()

        Thread {
            run {
                dialog.show()

            }
        }.start()
        dialog.findViewById<TextView>(R.id.tile).setText("修改标题")
        dialog.setOnCancelListener {
            Toast.makeText(this, "点击外部消失", Toast.LENGTH_SHORT).show()
        }

    }

    private fun fragmentDialog() {
        val dialog = CommonDialog.Build(this)
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
        val adapter = CommonRecyclerViewAdapter.Build()
            .setSize(list.size)
            .setLayoutId(R.layout.view_page_item)
            .build(this)

        adapter.setCommonRecyclerViewAdapterBackCall { holder, position ->
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
            R.id.test_dialog_lifecycle -> testDialogLifecycle()
            R.id.anim_dialog -> animDialog()
        }
    }

    private fun animDialog() {
        val dialog = CommonDialog.Build(this)
            .setLayout(R.layout.common_dia_log)
            .setText(R.id.tile, "自定义标题")
            .setText(R.id.message, "自定义内容")
            .setText(R.id.left_button, "确定")
            .setText(R.id.right_button, "取消")
            .setAnimation(R.id.imageView, R.anim.rotate)
            .setCanceledOnTouchOutside(true)
            .build()

        dialog.show()
    }

    private fun testDialogLifecycle() {
        val dialog = CommonDialog.Build(this)
            .setLayout(R.layout.common_dia_log)
            .setText(R.id.tile, "自定义标题")
            .setText(R.id.message, "点击确定销毁Activity")
            .setText(R.id.left_button, "自动销毁Dialog")
            .setText(R.id.right_button, "手动销毁Dialog")
            .setOnClickListener(R.id.left_button) {
                finish()
            }
            .setCanceledOnTouchOutside(true)
            .build()
        dialog.findViewById<Button>(R.id.right_button).setOnClickListener {
            // 经过测试多次调用dialog.dismiss()没有异常
            dialog.dismiss()
            dialog.dismiss()
        }
        dialog.show()
    }
}