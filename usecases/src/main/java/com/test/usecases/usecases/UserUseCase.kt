package com.test.usecases.usecases

import com.test.entities.ObjectResult
import com.test.entities.User
import com.test.repository.remote.http.interfaces.IUserDataSource
import com.test.usecases.interfaces.IUserUseCase

class UserUseCase(
    private val userDataSource: IUserDataSource
) : IUserUseCase {
    override suspend fun getUserList(): ObjectResult<List<User>> {
        return when (val response = userDataSource.getUserList()) {
            is ObjectResult.Success -> {
                ObjectResult.Success(response.data)
            }

            else -> {
                response
            }
        }
    }
}

