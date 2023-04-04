package com.test.repository.remote.http.models.response

import com.google.gson.annotations.SerializedName
import com.test.entities.User

data class UserResponse(
    val id: Int,
    val name: String,
    @SerializedName("username") val userName: String,
    val email: String,
    val phone: String,
) {
    fun toUser(): User {
        return User(
            id = this.id,
            name = this.name,
            userName = this.userName,
            email = this.email,
            phone = this.phone
        )
    }
}

fun List<UserResponse>.toUserList(): List<User> = this.map {
    it.toUser()
}
