package com.hojong.springbootexample.user.factory

import com.hojong.springbootexample.user.entity.User

open class UserFactory {
    fun of(name: String) =
        User(0, name)
}
