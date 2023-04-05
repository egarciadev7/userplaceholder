package com.test.repository.remote.http.models.response

import com.test.entities.Post

data class PostResponse(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String,
) {
    fun toPost(): Post {
        return Post(
            id = this.id,
            userId = this.userId,
            title = this.title,
            body = this.body,
        )
    }
}

fun List<PostResponse>.toPostsList(): List<Post> = this.map {
    it.toPost()
}

