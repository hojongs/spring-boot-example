package com.hojong.springbootexample.user.service

import com.hojong.springbootexample.user.dto.CreateUserRequest
import com.hojong.springbootexample.user.entity.User
import com.hojong.springbootexample.user.repository.UserRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun createUser(createUserRequest: CreateUserRequest): Mono<User> =
        User.of(createUserRequest.name)
            .toMono()
            .map { user -> userRepository.save(user) } // Indeed, we need io scheduler
}