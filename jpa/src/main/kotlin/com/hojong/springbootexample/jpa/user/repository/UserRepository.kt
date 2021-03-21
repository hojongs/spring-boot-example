package com.hojong.springbootexample.jpa.user.repository

import com.hojong.springbootexample.jpa.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByName(name: String): User?
}
