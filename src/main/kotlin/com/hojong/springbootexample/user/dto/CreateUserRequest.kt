package com.hojong.springbootexample.user.dto

data class CreateUserRequest(
    val name: String,
) {
    init {
        if (name.isEmpty())
            throw IllegalArgumentException("empty name")
    }
}
