package com.test.usecases.usecases

import com.test.entities.ObjectResult
import com.test.entities.Post
import com.test.repository.remote.http.interfaces.IPostDataSource
import com.test.usecases.interfaces.IPostUseCase

class PostUseCase(
    private val postDataSource: IPostDataSource
) : IPostUseCase {
    override suspend fun getPostByUserId(userId: Int): ObjectResult<List<Post>> {
        return when (val response = postDataSource.getPostsByUser(userId)) {
            is ObjectResult.Success -> {
                ObjectResult.Success(response.data)
            }

            else -> {
                response
            }
        }
    }
}


