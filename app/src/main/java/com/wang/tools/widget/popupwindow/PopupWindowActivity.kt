package com.wang.tools.widget.popupwindow

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.wang.javatools.widget.popupwindow.CommonPopupWindow
import com.wang.tools.R

class PopupWindowActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var imageView: ImageView
    private lateinit var standardPopupWindow: CommonPopupWindow
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup_window)

        val standard_popup_window = findViewById<Button>(R.id.standard_popup_window)
        val show_top = findViewById<Button>(R.id.show_top)
        val show_bottom = findViewById<Button>(R.id.show_bottom)
        val show_right = findViewById<Button>(R.id.show_right)
        val show_left = findViewById<Button>(R.id.show_left)

        imageView = findViewById(R.id.imageView)


        standardPopupWindow = CommonPopupWindow.Build()
            .setContext(this)
            .setLayout(R.layout.popup_window)
            .setHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
            .setWidth(ViewGroup.LayoutParams.WRAP_CONTENT)
            .Build();

        standard_popup_window.setOnClickListener(this)
        show_top.setOnClickListener(this)
        show_bottom.setOnClickListener(this)
        show_right.setOnClickListener(this)
        show_left.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if (v == null) {
            return
        }


        when (v.id) {
            R.id.standard_popup_window -> standardPopupWindow()
            R.id.show_top -> showTop()
            R.id.show_bottom -> showBottom()
            R.id.show_right -> showRight()
            R.id.show_left -> showLeft()
        }
    }

    private fun showLeft() {
//        standardPopupWindow.showBottomToLeft(imageView)
    }

    private fun showRight() {

    }

    private fun showBottom() {

    }

    private fun standardPopupWindow() {
        standardPopupWindow.showAsDropDown(imageView)
    }

    private fun showTop() {


    }
}