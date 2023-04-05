package com.test.usecases.interfaces

import com.test.entities.ObjectResult
import com.test.entities.Post

interface IPostUseCase {
    suspend fun getPostByUserId(userId: Int): ObjectResult<List<Post>>
}


