package com.valhallaonlineservices.eventspass.nativeappexam.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.valhallaonlineservices.eventspass.nativeappexam.R
import com.valhallaonlineservices.eventspass.nativeappexam.mvvms.models.Comments

class DisplayCommentsAdapter(private val context: Context, private val dataSet: List<Comments>)  :
    RecyclerView.Adapter<DisplayCommentsAdapter.DisplayCommentsViewHolder>() {

    inner class DisplayCommentsViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txvDisplayCommentsUsername: TextView = itemView.findViewById(R.id.txvDisplayCommentsUsername)
        val txvDisplayCommentsMessage: TextView = itemView.findViewById(R.id.txvDisplayCommentsMessage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisplayCommentsViewHolder {
        return DisplayCommentsViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_comments_list, parent, false))
    }

    override fun onBindViewHolder(holder: DisplayCommentsViewHolder, position: Int) {
        var viewholderDisplayComments = holder as? DisplayCommentsAdapter.DisplayCommentsViewHolder
        viewholderDisplayComments!!.txvDisplayCommentsUsername.text = dataSet.get(position).username
        viewholderDisplayComments!!.txvDisplayCommentsMessage.text = dataSet.get(position).message
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}
