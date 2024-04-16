package com.app.practicalrecycleviewpagination.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.app.practicalrecycleviewpagination.api.RetrofitInstance
import com.app.practicalrecycleviewpagination.model.Post
import com.app.practicalrecycleviewpagination.ui.PostPagingSource
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.ConcurrentHashMap

class MainViewModel : ViewModel() {
    private val apiService = RetrofitInstance.create()

   // private val computedResults = ConcurrentHashMap<Post, Result>()

    /*fun computeHeavyTask(post: Post): Result {
        return computedResults.computeIfAbsent(post) {
            // Perform heavy computation
            heavyComputation(post)
        }
    }*/


    fun getPosts(): Flow<PagingData<Post>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { PostPagingSource(apiService) }
        ).flow
    }


}
