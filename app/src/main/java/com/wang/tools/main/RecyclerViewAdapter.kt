package com.wang.tools.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wang.tools.R

class RecyclerViewAdapter(list: List<String>, context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mContext: Context = context
    private var mList: List<String> = list;
    private lateinit var mOnClickListener: OnClickListener;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(mContext).inflate(R.layout.recycler_button_item, parent, false)
        return RecyclerViewHolder(itemView);
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as RecyclerViewHolder
        holder.mButton!!.text = mList[position]
        holder.mButton.setOnClickListener {
            mOnClickListener.onClick(it,position)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

     fun setonClickListener(onClickListener: OnClickListener) {
        mOnClickListener = onClickListener
    }


    interface OnClickListener {
        fun onClick(view: View, position: Int)
    }
}
