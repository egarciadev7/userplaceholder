package com.test.entities

import java.io.Serializable

data class User(
    val id: Int,
    val name: String,
    val userName: String,
    val email: String,
    val phone: String
) : Serializable

