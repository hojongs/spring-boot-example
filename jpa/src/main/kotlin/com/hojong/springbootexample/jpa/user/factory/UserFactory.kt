package com.hojong.springbootexample.jpa.user.factory

import com.hojong.springbootexample.jpa.user.entity.User

open class UserFactory {
    fun of(name: String) =
        User(0, name)
}
