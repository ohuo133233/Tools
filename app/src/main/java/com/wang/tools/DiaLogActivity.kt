package com.wang.tools

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.wang.javatools.widget.dialog.CommonDialog

class DiaLogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dia_log)

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
}