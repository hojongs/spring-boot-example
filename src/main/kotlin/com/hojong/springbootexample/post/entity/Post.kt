package com.hojong.springbootexample.post.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Post(
    @Id
    @GeneratedValue
    val id: Long,
    val title: String,
    val content: String,
) {
    companion object {
        fun of(title: String, content: String) =
            Post(0, title, content)
    }
}
