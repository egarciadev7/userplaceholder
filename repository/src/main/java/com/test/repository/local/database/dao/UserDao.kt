package com.test.repository.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.test.repository.local.database.models.UserDb

@Dao
interface UserDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertUsers(users: List<UserDb>)

    @Query("SELECT * FROM User")
    suspend fun getUsersList(): List<UserDb>

    @Query("SELECT * FROM User WHERE name LIKE '%' || :term || '%'")
    suspend fun getUsersListByTerm(term: String): List<UserDb>
}


