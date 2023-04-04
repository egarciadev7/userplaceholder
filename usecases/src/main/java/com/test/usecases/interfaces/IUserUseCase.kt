package com.test.usecases.interfaces

import com.test.entities.ObjectResult
import com.test.entities.User

interface IUserUseCase {
    suspend fun getUserList(): ObjectResult<List<User>>
}

