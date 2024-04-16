package com.app.practicalrecycleviewpagination.ui.adapter

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.practicalrecycleviewpagination.R
import com.app.practicalrecycleviewpagination.model.Post

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
    private val bodyTextView: TextView = itemView.findViewById(R.id.bodyTextView)

    fun bind(post: Post) {
        titleTextView.text = post.title
        bodyTextView.text = post.body
    }

   /* fun updateComputedDetails(result: Result) {
        Log.d( "","Computed Result: ${result.value}")
    }*/
}
