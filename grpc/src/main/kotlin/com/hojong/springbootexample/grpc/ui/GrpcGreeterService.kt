package com.hojong.springbootexample.grpc.ui

import io.grpc.examples.helloworld.GreeterGrpcKt
import io.grpc.examples.helloworld.HelloReply
import io.grpc.examples.helloworld.HelloRequest
import org.lognet.springboot.grpc.GRpcService
import kotlin.coroutines.EmptyCoroutineContext

@GRpcService
class GrpcGreeterService : GreeterGrpcKt.GreeterCoroutineImplBase(
    coroutineContext = EmptyCoroutineContext
) {
    override suspend fun sayHello(request: HelloRequest): HelloReply =
        HelloReply.newBuilder()
            .apply {
                message = "Hello ${request.name}"
            }
            .build()
}
