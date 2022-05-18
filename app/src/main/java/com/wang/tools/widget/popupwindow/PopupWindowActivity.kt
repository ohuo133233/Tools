package com.wang.tools.widget.popupwindow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import com.wang.javatools.widget.popupwindow.CommonPopupWindow
import com.wang.tools.R

class PopupWindowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup_window)
        val standard_popup_window = findViewById<Button>(R.id.standard_popup_window)

        standard_popup_window.setOnClickListener {
            val commonPopupWindow = CommonPopupWindow.Build()
                .setContext(this)
                .setLayout(R.layout.popup_window)
                .setHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setWidth(ViewGroup.LayoutParams.WRAP_CONTENT)
                .Build();
            commonPopupWindow.showAsDropDown(standard_popup_window)
        }
    }
}