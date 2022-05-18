package com.wang.tools.widget.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wang.javatools.widget.recyclerview.CommonRecyclerViewAdapter
import com.wang.tools.R

class MultiLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi_layout)

        var list = listOf(
            "1   2   3",
            "4   5   6",
            "7   8   9",
        )


        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)


        val recyclerViewAdapter = CommonRecyclerViewAdapter.Build<String>()
            .setContext(this)
            .setLayoutIdList(R.layout.recycler_text_item, R.layout.recycler_button_item,R.layout.recycler_image_item)
            .setDataList(list)
            .build()

//        recyclerViewAdapter.setBaseRecyclerViewAdapterBackCall { holder, position ->
//            holder.getView<TextView>(
//                R.id.text_view
//            ).text = list[position]
//        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerViewAdapter
    }
}