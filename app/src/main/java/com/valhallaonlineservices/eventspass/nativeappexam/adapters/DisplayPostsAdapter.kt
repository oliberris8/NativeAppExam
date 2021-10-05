package com.valhallaonlineservices.eventspass.nativeappexam.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.valhallaonlineservices.eventspass.nativeappexam.R
import com.valhallaonlineservices.eventspass.nativeappexam.mvvms.models.Posts

class DisplayPostsAdapter(private val context: Context, private val dataSet: List<Posts>) :
    RecyclerView.Adapter<DisplayPostsAdapter.DisplayPostsViewHolder>() {
    var adapterListener: DisplayPostsAdapterListener? = null

    interface DisplayPostsAdapterListener {
        fun onPostClicked(postId: Int)
    }

    inner class DisplayPostsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ctlDisplayPostsCard: ConstraintLayout = itemView.findViewById(R.id.ctlDisplayPostsCard)
        val txvDisplayPostsTitle: TextView = itemView.findViewById(R.id.txvDisplayPostsTitle)
        val txvDisplayPostsUsername: TextView = itemView.findViewById(R.id.txvDisplayPostsUsername)
        val txvDisplayPostsBody: TextView = itemView.findViewById(R.id.txvDisplayPostsBody)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisplayPostsViewHolder {
        return DisplayPostsViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_posts_list, parent, false))
    }

    override fun onBindViewHolder(holder: DisplayPostsViewHolder, position: Int) {
        var viewholderDisplayPosts = holder as? DisplayPostsAdapter.DisplayPostsViewHolder
        viewholderDisplayPosts!!.txvDisplayPostsTitle.text = dataSet.get(position).title
        viewholderDisplayPosts!!.txvDisplayPostsUsername.text = "by " + dataSet.get(position).username
        viewholderDisplayPosts!!.txvDisplayPostsBody.text = dataSet.get(position).body

        viewholderDisplayPosts!!.ctlDisplayPostsCard.setOnClickListener {
            sendPostClicked(dataSet.get(position).postId)
        }
        viewholderDisplayPosts!!.txvDisplayPostsUsername.setOnClickListener {
            sendPostClicked(dataSet.get(position).postId)
        }
        viewholderDisplayPosts!!.txvDisplayPostsUsername.setOnClickListener {
            sendPostClicked(dataSet.get(position).postId)
        }
        viewholderDisplayPosts!!.txvDisplayPostsBody.setOnClickListener {
            sendPostClicked(dataSet.get(position).postId)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    private fun sendPostClicked(postId: Int) {
        adapterListener?.onPostClicked(postId)
    }
}
