package com.hojong.springbootexample.greeter.controller

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
        HelloReply
            .newBuilder()
            .setMessage("Hello ${request.name}")
            .build()
}

//@GRpcService
//class GrpcGreeterService : GreeterGrpc.GreeterImplBase() {
//    override fun sayHello(request: HelloRequest, responseObserver: StreamObserver<HelloReply>) =
//        HelloReply
//            .newBuilder()
//            .setMessage("Hello ${request.name}")
//            .build()
//            .run {
//                responseObserver.onNext(this)
//                responseObserver.onCompleted()
//            }
//}
