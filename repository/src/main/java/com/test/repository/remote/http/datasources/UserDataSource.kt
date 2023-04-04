package com.test.repository.remote.http.datasources

import com.test.entities.ObjectResult
import com.test.entities.User
import com.test.repository.remote.http.interfaces.IUserDataSource
import com.test.repository.remote.http.models.response.toUserList
import com.test.repository.remote.http.services.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserDataSource(private val userService: UserService) : IUserDataSource {
    override suspend fun getUserList(): ObjectResult<List<User>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = userService.getUserList()
                if (!response.isSuccessful) {
                    ObjectResult.Failure(Exception(response.errorBody().toString()))
                } else {
                    ObjectResult.Success(response.body()!!.toUserList())
                }
            } catch (e: Exception) {
                ObjectResult.Failure(e)
            }
        }
    }
}