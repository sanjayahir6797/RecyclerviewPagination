package com.app.practicalrecycleviewpagination.api

import com.app.practicalrecycleviewpagination.model.Post
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>
}
