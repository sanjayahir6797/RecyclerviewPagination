package com.app.practicalrecycleviewpagination.ui.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.app.practicalrecycleviewpagination.R
import com.app.practicalrecycleviewpagination.model.Post
import com.app.practicalrecycleviewpagination.ui.activity.DetailedViewActivity
import com.app.practicalrecycleviewpagination.ui.viewmodel.MainViewModel
import com.app.practicalrecycleviewpagination.utils.MemoizationUtil.memoize
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random


class PostAdapter : PagingDataAdapter<Post, PostViewHolder>(POST_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)//?.let { holder.bind(it) }
        post?.let {

            holder.bind(it)
            // Perform heavy computation asynchronously
            val computationJob = CoroutineScope(Dispatchers.Default).launch {
                val startTime = System.currentTimeMillis()
                val result = memoizedHeavyComputation (it.id)
                val endTime = System.currentTimeMillis()

                // Log the time taken for the heavy computation process
                val elapsedTime = endTime - startTime
                Log.d("HeavyComputation", "Item ${it.id}: Time taken = $elapsedTime ms")

                // Update UI on the main thread
                withContext(Dispatchers.Main) {
                   // holder.bind(result)
                }
            }

        // Cancel the computation job if the view holder is recycled
        holder.itemView.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(view: View) {}

            override fun onViewDetachedFromWindow(view: View) {
                computationJob.cancel()
            }
        })
       }

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, DetailedViewActivity::class.java).apply {
                putExtra("postId", post?.id)
                putExtra("postTitle", post?.title)
            }
            it.context.startActivity(intent)
        }

    }
    private fun performHeavyComputation(input: Int): Long {
        // Simulating heavy computation by generating a random number
        return (1..input).sumOf { Random.nextLong() }
    }
    // Memoized heavy computation function
    private val memoizedHeavyComputation = ::performHeavyComputation.memoize()

    companion object {
        private val POST_COMPARATOR = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
                oldItem == newItem
        }
    }
}
