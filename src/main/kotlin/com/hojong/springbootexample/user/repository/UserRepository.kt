package com.hojong.springbootexample.user.repository

import com.hojong.springbootexample.user.entity.User
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface UserRepository : ReactiveCrudRepository<User, Long> {
    fun findByName(name: String): Mono<User>
}
