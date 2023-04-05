package com.test.repository.remote.http.interfaces

import com.test.entities.ObjectResult
import com.test.entities.Post

interface IPostDataSource {
    suspend fun getPostsByUser(userId: Int): ObjectResult<List<Post>>
}
