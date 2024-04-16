package com.app.practicalrecycleviewpagination.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.practicalrecycleviewpagination.databinding.ActivityMainBinding
import com.app.practicalrecycleviewpagination.ui.viewmodel.MainViewModel
import com.app.practicalrecycleviewpagination.ui.adapter.PostAdapter
import kotlinx.coroutines.flow.collectLatest


class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val postAdapter = PostAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = postAdapter
        }

        // Observe the items from the ViewModel's Flow and submit them to the adapter
        lifecycleScope.launchWhenStarted {
            binding.progressBar.visibility = View.VISIBLE
            viewModel.getPosts().collectLatest { pagingData ->
                binding.progressBar.visibility = View.GONE

                postAdapter.submitData(pagingData)
            }
        }



    }
}