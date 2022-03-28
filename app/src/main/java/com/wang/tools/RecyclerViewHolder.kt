package com.wang.tools

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
    val mButton = itemView?.findViewById<TextView>(R.id.button_view)


}
