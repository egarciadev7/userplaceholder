package com.test.repository.remote.http.datasources

import com.test.entities.ObjectResult
import com.test.entities.Post
import com.test.repository.remote.http.interfaces.IPostDataSource
import com.test.repository.remote.http.models.response.toPostsList
import com.test.repository.remote.http.services.PostService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostDataSource(private val postService: PostService) : IPostDataSource {
    override suspend fun getPostsByUser(userId: Int): ObjectResult<List<Post>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = postService.getPostByUserId(userId)
                if (!response.isSuccessful) {
                    ObjectResult.Failure(Exception(response.errorBody().toString()))
                } else {
                    ObjectResult.Success(response.body()!!.toPostsList())
                }
            } catch (e: Exception) {
                ObjectResult.Failure(e)
            }
        }
    }
}
