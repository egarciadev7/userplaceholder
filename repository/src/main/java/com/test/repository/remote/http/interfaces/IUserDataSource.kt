package com.test.repository.remote.http.interfaces

import com.test.entities.ObjectResult
import com.test.entities.User

interface IUserDataSource {
    suspend fun getUserList(): ObjectResult<List<User>>
}
