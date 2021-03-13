package com.hojong.springbootexample.greeter.controller

import com.hojong.springbootexample.App
import io.grpc.Channel
import io.grpc.ManagedChannelBuilder
import io.grpc.examples.helloworld.GreeterGrpcKt
import io.grpc.examples.helloworld.HelloRequest
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor

@SpringBootTest(
    classes = [App::class],
)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@ActiveProfiles("test")
internal class GrpcGreeterServiceTest {

    private val channel: Channel =
        ManagedChannelBuilder
            .forAddress("localhost", 6565)
            .usePlaintext()
            .build()

    @Test
    fun sayHello() {
        val stub = GreeterGrpcKt.GreeterCoroutineStub(channel)
        val req =
            HelloRequest
                .newBuilder()
                .setName("hojong")
                .build()
        runBlocking {
            val reply = stub.sayHello(req)
            println(reply.message)
        }
    }
}
