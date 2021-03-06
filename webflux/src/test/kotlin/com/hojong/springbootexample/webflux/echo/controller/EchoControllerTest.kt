package com.hojong.springbootexample.webflux.echo.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.hojong.springbootexample.webflux.echo.dto.EchoMessage
import com.hojong.springbootexample.webflux.App
import com.hojong.springbootexample.webflux.test.TestBase
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.util.CollectionUtils

@AutoConfigureWebTestClient
@SpringBootTest(
    classes = [App::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@TestBase
internal class EchoControllerTest(
    private val webTestClient: WebTestClient
) {
    private val mapper = jacksonObjectMapper()

    @Test
    fun echo() {
        val message = "hello"
        val queryParams = CollectionUtils.toMultiValueMap(
            mapOf(
                "message" to listOf(message),
            )
        )

        webTestClient
            .get()
            .uri { builder ->
                builder
                    .path("/echo/echo")
                    .queryParams(queryParams)
                    .build()
            }
            .exchange()
            .expectStatus().isOk
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .consumeWith { result ->
                val reply = mapper.readValue(result.responseBody, EchoMessage::class.java)
                assertThat(reply.message)
                    .isEqualTo(message)
            }
    }
}
