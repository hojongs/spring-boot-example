package com.hojong.springbootexample.post.dto

import com.hojong.springbootexample.post.entity.Post

data class CreatePostRequet(
    val userId: Long,
    val title: String,
    val content: String,
) {
    init {
        if (userId == 0L)
            throw IllegalArgumentException("userId was 0")

        if (title.isEmpty())
            throw IllegalArgumentException("title was empty")

        if (content.isEmpty())
            throw IllegalArgumentException("content was empty")
    }

    fun toPost() =
        Post.createTransient(title, content, Post.MyMessage("temp"))
}
