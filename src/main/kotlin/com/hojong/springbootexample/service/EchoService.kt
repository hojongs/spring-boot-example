package com.hojong.springbootexample.service

import com.hojong.springbootexample.dto.EchoMessage
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class EchoService {
    fun echo(message: String): Mono<EchoMessage> =
        Mono.fromCallable { EchoMessage("Reply $message") }
}
