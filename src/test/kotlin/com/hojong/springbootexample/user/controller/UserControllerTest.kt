package com.hojong.springbootexample.user.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.hojong.springbootexample.App
import com.hojong.springbootexample.test.TestBase
import com.hojong.springbootexample.user.entity.User
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.reactive.awaitSingleOrNull
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.returnResult
import org.springframework.util.CollectionUtils
import org.springframework.util.MultiValueMap
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import reactor.kotlin.test.test
import kotlin.system.measureTimeMillis

@AutoConfigureWebTestClient
@SpringBootTest(
    classes = [App::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@TestBase
internal class UserControllerTest(
    private val webTestClient: WebTestClient
) {
    private val mapper = jacksonObjectMapper()
    private val userServiceClient = UserServiceClient(webTestClient)

    @Nested
    inner class CreateUser {
        @Test
        fun success() {
            val name = "hojong"

            userServiceClient
                .createUser(name)
                .test()
                .assertNext { user ->
                    println(user)

                    assertThat(user.id)
                        .isNotEqualTo(0)
                    assertThat(user.name)
                        .isEqualTo(name)
                }
                .verifyComplete()
        }

        @Test
        fun performance() {
            val prefix = "hojong2"
            val count = 10000

            val start = System.currentTimeMillis()

            Flux.range(1, count)
                .map { index -> "$prefix-$index" }
                .parallel()
                .runOn(Schedulers.boundedElastic())
                .flatMap({ name -> userServiceClient.createUser(name) }, true)
                .subscribe { user ->
                    val time = System.currentTimeMillis() - start
                    println("$user : $time")
                }

            Thread.sleep(3000)
        }
    }

    class UserServiceClient(
        private val webTestClient: WebTestClient,
    ) {
        fun createUser(name: String): Mono<User> =
            webTestClient
                .post()
                .uri { builder ->
                    val queryParams = buildQueryParams(name)

                    builder
                        .path("/user")
                        .queryParams(queryParams)
                        .build()
                }
                .exchange()
                .expectStatus().isOk
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .returnResult<User>()
                .responseBody.last()

        private fun buildQueryParams(name: String): MultiValueMap<String, String> =
            CollectionUtils.toMultiValueMap(
                mapOf(
                    "name" to listOf(name),
                )
            )
    }
}
