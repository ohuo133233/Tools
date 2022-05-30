package com.wang.tools.widget.recyclerview

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wang.javatools.widget.recyclerview.CommonRecyclerViewAdapter
import com.wang.tools.R

class StandardRecyclerViewActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var list: MutableList<String>
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: CommonRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standard_recycler_view)
        initView()
        initData()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerViewAdapter = CommonRecyclerViewAdapter.Build()
            .setContext(this)
            .setLayoutId(R.layout.recycler_text_item)
            .setSize(list.size)
            .build()

        recyclerViewAdapter.setBaseRecyclerViewAdapterBackCall { holder, position ->
            holder.getView<TextView>(
                R.id.text_view
            ).text = list[position]
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerViewAdapter

    }

    private fun initData() {
        list = mutableListOf(
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

    }

    private fun initView() {
        recyclerView = findViewById(R.id.recycler_view)
        val add = findViewById<Button>(R.id.add)
        val remove = findViewById<Button>(R.id.remove)

        add.setOnClickListener(this)
        remove.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        if (v == null) {
            return
        }

        when (v.id) {
            R.id.add -> add()
            R.id.remove -> remove()
        }
    }

    private fun add() {
        list.add("新增数据")
        recyclerViewAdapter.notifyDataSetChanged()
    }

    private fun remove() {
        list.removeAt(list.size - 1)
        recyclerViewAdapter.notifyDataSetChanged()
    }
}