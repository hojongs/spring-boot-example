package com.hojong.springbootexample.user.controller

import com.hojong.springbootexample.utils.LoggerProvider
import com.hojong.springbootexample.user.dto.CreateUserRequest
import com.hojong.springbootexample.user.entity.User
import com.hojong.springbootexample.user.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService,
) {
    companion object : LoggerProvider()

    @PostMapping
    fun createUser(@RequestParam name: String): Mono<User> =
        CreateUserRequest(name)
            .toMono()
            .flatMap { userService.createUser(it) }
            .doOnNext { logger.info("hi") }
}
