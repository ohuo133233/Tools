package com.wang.tools.widget.recyclerview

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.wang.tools.R

class RecyclerViewActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        initView()
    }

    private fun initView() {
        val standard = findViewById<Button>(R.id.standard)
        val addItemDecoration = findViewById<Button>(R.id.add_item_decoration)
        val multiLayout = findViewById<Button>(R.id.multi_layout)
        val rightSlideDelete = findViewById<Button>(R.id.right_slide_delete)
        val dragSort = findViewById<Button>(R.id.drag_sort)

        standard.setOnClickListener(this)
        addItemDecoration.setOnClickListener(this)
        multiLayout.setOnClickListener(this)
        rightSlideDelete.setOnClickListener(this)
        dragSort.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v == null) {
            return
        }
        when (v.id) {
            R.id.standard -> standardRecyclerView()
            R.id.add_item_decoration -> addItemDecoration()
            R.id.multi_layout -> multiLayout()
            R.id.right_slide_delete -> rightSlideDelete()
            R.id.drag_sort -> dragSort()
        }
    }

    private fun dragSort() {
        val intent = Intent(this, DragSortActivity::class.java)
        startActivity(intent)
    }

    private fun rightSlideDelete() {
        val intent = Intent(this, RightSlideDeleteActivity::class.java)
        startActivity(intent)
    }

    private fun multiLayout() {
        val intent = Intent(this, MultiLayoutActivity::class.java)
        startActivity(intent)
    }

    private fun addItemDecoration() {
        val intent = Intent(this, AddItemDecorationActivity::class.java)
        startActivity(intent)
    }

    private fun standardRecyclerView() {
        val intent = Intent(this, StandardRecyclerViewActivity::class.java)
        startActivity(intent)
    }
}