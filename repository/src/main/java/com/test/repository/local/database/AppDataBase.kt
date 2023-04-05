package com.test.repository.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.repository.local.database.dao.UserDao
import com.test.repository.local.database.models.UserDb

@Database(
    entities = [
        UserDb::class,
    ],
    version = 1,
    exportSchema = false
)

abstract class AppDataBase : RoomDatabase() {
    abstract val userDao: UserDao
}

