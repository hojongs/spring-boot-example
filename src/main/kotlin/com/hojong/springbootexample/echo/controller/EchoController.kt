package com.hojong.springbootexample.echo.controller

import com.hojong.springbootexample.echo.dto.EchoMessage
import com.hojong.springbootexample.echo.service.EchoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/echo")
class EchoController(
    private val echoService: EchoService,
) {
    @GetMapping("/echo")
    fun echo(@RequestParam message: String): Mono<EchoMessage> =
        echoService.echo(message)
}
