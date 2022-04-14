package com.wang.tools.main

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wang.tools.R

class RecyclerViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
    val mButton = itemView?.findViewById<TextView>(R.id.button_view)


}
