package com.test.repository.local.database.interfaces

import com.test.entities.User

interface IUserLocalDataSource {
    suspend fun saveUsers(users: List<User>)
    suspend fun getUserList(): List<User>
    suspend fun getUsersListByTerm(term: String): List<User>
}


