package com.hojong.springbootexample.grpc.ui

import com.hojong.springbootexample.grpc.App
import com.hojong.springbootexample.grpc.test.TestBase
import io.grpc.Channel
import io.grpc.ManagedChannelBuilder
import io.grpc.examples.helloworld.GreeterGrpcKt
import io.grpc.examples.helloworld.HelloRequest
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(
    classes = [App::class],
)
@TestBase
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
            HelloRequest.newBuilder()
                .apply {
                    name = "hojong"
                }
                .build()

        runBlocking {
            val reply = stub.sayHello(req)
            println(reply.message)
        }
    }
}
