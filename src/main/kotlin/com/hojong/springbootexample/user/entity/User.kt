package com.hojong.springbootexample.user.entity

import com.hojong.springbootexample.user.factory.UserFactory
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class User(
    @Id
    @GeneratedValue
    val id: Long,
    val name: String,
) {
    companion object : UserFactory()
}
