package com.hojong.springbootexample.user.entity

import com.hojong.springbootexample.user.factory.UserFactory
import org.springframework.data.annotation.Id

data class User(
    @Id
    val id: Long,
    val name: String,
) {
    companion object : UserFactory()
}
