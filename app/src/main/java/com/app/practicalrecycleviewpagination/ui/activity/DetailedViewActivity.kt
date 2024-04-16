package com.app.practicalrecycleviewpagination.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.app.practicalrecycleviewpagination.R

class DetailedViewActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_view)
        val postId = intent.getIntExtra("postId", 0)
        val postTitle = intent.getStringExtra("postTitle")

        // Display the post details in the detailed view
        findViewById<TextView>(R.id.textViewPostId).text = "Post ID:$postId"
        findViewById<TextView>(R.id.textViewPostTitle).text = "Post Title:$postTitle"
    }
}