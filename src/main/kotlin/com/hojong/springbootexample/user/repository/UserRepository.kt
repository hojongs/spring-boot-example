package com.hojong.springbootexample.user.repository

import com.hojong.springbootexample.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByName(name: String): User?
}
