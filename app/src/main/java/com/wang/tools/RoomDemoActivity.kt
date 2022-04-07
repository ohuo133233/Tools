package com.wang.tools

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wang.javatools.room.RoomManager
import com.wang.javatools.room.User
import com.wang.javatools.widget.dialog.CommonDialog
import com.wang.javatools.widget.recyclerview.BaseRecyclerViewAdapter

/**
 * TODO RecycleView可以增加右滑删除
 *
 *
 */
class RoomDemoActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mRoomManager: RoomManager
    private lateinit var mBaseDataList: RecyclerView
    private lateinit var dataList: MutableList<User>
    private lateinit var adapter: BaseRecyclerViewAdapter<User>
    private lateinit var mCommonDialog: CommonDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_demo)
        initView()
        initRoom()
        initRecycleView()
        initDiaLog()
    }

    private fun initDiaLog() {
        mCommonDialog = CommonDialog.Build()
            .setContext(this)
            .setLayout(R.layout.base_data_dia_log)
            .setWidth(ViewGroup.LayoutParams.WRAP_CONTENT)
            .setHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
            .setCanceledOnTouchOutside(true)
            .build()
    }

    private fun initRecycleView() {
        val user1 = User();
        user1.name = "测试1"
        user1.age = 10
        user1.sex = "男"

        val user2 = User();
        user2.name = "测试2"
        user2.age = 10
        user2.sex = "男"

        val user3 = User();
        user3.name = "测试3"
        user3.age = 10
        user3.sex = "男"


        dataList = mutableListOf(user1, user2, user3)
        adapter = BaseRecyclerViewAdapter.Build<User>()
            .setContext(this)
            .setDataList(dataList)
            .setLayoutId(R.layout.room_demo_item)
            .build()

        mBaseDataList.adapter = adapter
        mBaseDataList.layoutManager = LinearLayoutManager(this)
        adapter.setBaseRecyclerViewAdapterBackCall { holder, position ->
            holder.getView<TextView>(R.id.name).text = dataList[position].name
            holder.getView<TextView>(R.id.age).text = dataList[position].age.toString()
            holder.getView<TextView>(R.id.sex).text = dataList[position].sex.toString()

        }
    }

    private fun initRoom() {
        mRoomManager = RoomManager()
        mRoomManager.initRoom(this.application)
    }

    private fun initView() {
        val add = findViewById<Button>(R.id.add)
        val delete = findViewById<Button>(R.id.delete)
        val upData = findViewById<Button>(R.id.up_data)
        val query = findViewById<Button>(R.id.query)


        mBaseDataList = findViewById(R.id.base_data_list)

        add.setOnClickListener(this)
        delete.setOnClickListener(this)
        upData.setOnClickListener(this)
        query.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v == null) {
            return
        }
        when (v.id) {
            R.id.add -> add()
            R.id.delete -> delete()
            R.id.up_data -> upData()
            R.id.query -> query()
        }
    }

    private fun add() {
        mCommonDialog.getView<TextView>(R.id.tile).text = "添加"
        mCommonDialog.show()
        mCommonDialog.getView<Button>(R.id.determine).setOnClickListener {
            val user = User()
            user.name = mCommonDialog.getView<EditText>(R.id.name).text.toString().trim()
            user.age = mCommonDialog.getView<EditText>(R.id.age).text.toString().trim().toInt()
            user.sex = mCommonDialog.getView<EditText>(R.id.sex).text.toString().trim()
            mRoomManager.insert(user)
            mRoomManager.query()
            adapter.notifyDataSetChanged()
            mCommonDialog.hide()
        }

        mCommonDialog.getView<Button>(R.id.cancel).setOnClickListener {
            mCommonDialog.hide()
        }


    }

    private fun delete() {
        val user = User()
        user.name = "测试add"
        user.age = 1
        user.sex = "男"
        mRoomManager.delete(user)
        mRoomManager.query()
        adapter.notifyDataSetChanged()
    }


    private fun upData() {
        val user = User()
        user.name = "测试add"
        user.age = 1
        user.sex = "男"
        mRoomManager.upData(user)
        mRoomManager.query()
        adapter.notifyDataSetChanged()
    }

    private fun query() {
        val query = mRoomManager.query()
        dataList.clear()
        dataList.addAll(query)
        adapter.notifyDataSetChanged()

    }

}