package com.wang.tools.widget.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wang.javatools.widget.recyclerview.BaseRecyclerViewAdapter
import com.wang.tools.R

class StandardRecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standard_recycler_view)

        var list = listOf(
            "1   2   3",
            "4   5   6",
            "7   8   9",
            "10 11 12",
            "13 14 15",
            "16 17 18",
            "19 20 21",
            "22 23 24",
            "25 26 27",
            "28 29 30",
            "31 32 33",
            "34 35 36",
            "37 38 39",
            "40 41 42",
            "43 44 45",
            "46 47 48",
        )


        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)


        val recyclerViewAdapter = BaseRecyclerViewAdapter.Build<String>()
            .setContext(this)
            .setLayoutId(R.layout.recycler_text_item)
            .setDataList(list)
            .build()

        recyclerViewAdapter.setBaseRecyclerViewAdapterBackCall { holder, position ->
            holder.getView<TextView>(
                R.id.text_view
            ).text = list[position]
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerViewAdapter
    }
}