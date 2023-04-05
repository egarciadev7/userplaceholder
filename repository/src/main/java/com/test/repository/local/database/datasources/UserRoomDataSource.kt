package com.test.repository.local.database.datasources

import com.test.entities.User
import com.test.repository.local.database.dao.UserDao
import com.test.repository.local.database.interfaces.IUserLocalDataSource
import com.test.repository.local.database.models.toDB
import com.test.repository.local.database.models.toEntity

class UserRoomDataSource(private val userDao: UserDao) : IUserLocalDataSource {
    override suspend fun saveUsers(users: List<User>) {
        userDao.insertUsers(users.map { it.toDB() })
    }

    override suspend fun getUserList(): List<User> {
        return userDao.getUsersList().map { it.toEntity() }
    }

    override suspend fun getUsersListByTerm(term: String): List<User> {
        return userDao.getUsersListByTerm(term).map { it.toEntity() }
    }
}


