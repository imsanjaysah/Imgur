package com.sanjay.imgur.ui.detail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sanjay.imgur.R
import com.sanjay.imgur.data.database.model.Comment
import kotlinx.android.synthetic.main.item_list_comment.view.*


class CommentsAdapter : ListAdapter<Comment, CommentsAdapter.ItemViewholder>(DiffCallback()) {

    private var clickListener: ((Comment) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_comment, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CommentsAdapter.ItemViewholder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Comment) = with(itemView) {
            with(item) {
                txt_comment_body.text = message
                txt_date.text = timeStamp

            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Comment>() {
    override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem == newItem
    }
}