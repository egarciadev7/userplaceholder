package com.test.usecases.usecases

import com.test.entities.ObjectResult
import com.test.entities.User
import com.test.repository.local.database.interfaces.IUserLocalDataSource
import com.test.repository.remote.http.interfaces.IUserDataSource
import com.test.usecases.interfaces.IUserUseCase
import java.lang.Exception

class UserUseCase(
    private val userDataSource: IUserDataSource,
    private val userRoomDataSource: IUserLocalDataSource
) : IUserUseCase {
    override suspend fun getUserList(): ObjectResult<List<User>> {
        return try {
            val users = getLocalUserList()
            if (users.isNotEmpty()) {
                ObjectResult.Success(users)
            } else {
                val result = userDataSource.getUserList()
                if (result is ObjectResult.Success) {
                    saveLocalUserList(result.data)
                    ObjectResult.Success(result.data)
                } else {
                    result
                }
            }
        } catch (e: Exception) {
            ObjectResult.Failure(e)
        }
    }

    private suspend fun getLocalUserList(): List<User> {
        return userRoomDataSource.getUserList()
    }

    private suspend fun saveLocalUserList(users: List<User>) {
        userRoomDataSource.saveUsers(users)
    }
}



