package com.test.repository.remote.http.models.response

import com.test.entities.User

data class UserResponse(val id: Int, val name: String, val userName: String, val email: String) {
    fun toUser(): User {
        return User(
            id = this.id,
            name = this.name,
            userName = this.userName,
            email = this.email,
        )
    }
}

fun List<UserResponse>.toUserList(): List<User> = this.map {
    it.toUser()
}