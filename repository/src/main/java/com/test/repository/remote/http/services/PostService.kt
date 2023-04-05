package com.test.repository.remote.http.services

import com.test.repository.remote.http.models.response.PostResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PostService {
    @GET("posts")
    suspend fun getPostByUserId(
        @Query("userId") userId: Int
    ): Response<List<PostResponse>>
}
