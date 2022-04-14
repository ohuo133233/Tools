package com.wang.tools.recyclerview

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wang.javatools.widget.recyclerview.BaseRecyclerViewAdapter
import com.wang.tools.R
import java.util.*

class DragSortActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drag_sort)
        var list = mutableListOf(
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


        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
            // 同来设置 拖拽移动，或移动删除
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val swiped = ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN

                // 第一个参数拖动，第二个删除侧滑
                return makeMovementFlags(dragFlags, swiped)
            }

            //在拖动中不断的回调这个方法
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val oldPosition = viewHolder.adapterPosition
                val newPosition = target.adapterPosition

                // 改变实际的数据集
                Collections.swap(list, oldPosition, newPosition)
                recyclerViewAdapter.notifyItemMoved(oldPosition, newPosition)
                return false
            }

            //处理侧滑
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                list.removeAt(position)
                recyclerViewAdapter.notifyItemRemoved(position)
            }

            override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                    viewHolder!!.itemView.setBackgroundColor(Color.parseColor("#303F9F"))
                }
            }

            override fun clearView(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ) {
                super.clearView(recyclerView, viewHolder)
                viewHolder.itemView.setBackgroundColor(Color.TRANSPARENT)
            }
        })


        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}