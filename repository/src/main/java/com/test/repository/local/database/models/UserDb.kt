package com.test.repository.local.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.entities.User

@Entity(tableName = "User")
data class UserDb(
    @PrimaryKey
    val id: Int,
    val name: String,
    val userName: String,
    val email: String,
    val phone: String
)

fun UserDb.toEntity() = User(
    id = this.id,
    name = this.name,
    userName = this.userName,
    email = this.email,
    phone = this.phone,
)

fun User.toDB() = UserDb(
    id = this.id,
    name = this.name,
    userName = this.userName,
    email = this.email,
    phone = this.phone,
)

