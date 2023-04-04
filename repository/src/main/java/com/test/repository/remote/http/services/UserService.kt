package com.test.repository.remote.http.services

import com.test.repository.remote.http.models.response.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface UserService {
    @GET("users")
    suspend fun getUserList(
    ): Response<List<UserResponse>>
}
