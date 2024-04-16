package com.app.practicalrecycleviewpagination.ui

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.practicalrecycleviewpagination.api.ApiService
import com.app.practicalrecycleviewpagination.model.Post

class PostPagingSource(private val apiService: ApiService) : PagingSource<Int, Post>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = apiService.getPosts()
            LoadResult.Page(
                data = response,
                prevKey = null, // Paging from the first page, so no previous key
                nextKey = if (response.isEmpty()) null else nextPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        TODO("Not yet implemented")
    }






}
