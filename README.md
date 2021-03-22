# Spring Boot Example

## Blog Posts

### Start Spring Boot with Spring Initializer (Kotlin)
[Blog Post](https://medium.com/jongho-developer/start-spring-boot-with-spring-initializer-523f47600ee2?source=friends_link&sk=a0772af9888c1e09ce017292b3fa2d90)
- [webflux module][webflux]
- [build script][webflux build script]

### Start HTTP Server with Spring Webflux (Kotlin)
[Blog Post](https://medium.com/jongho-developer/start-http-server-with-spring-webflux-2c07828241d0?source=friends_link&sk=4082c493b6cb1b44c5bc3aaea5e6f40c)
- [webflux module][webflux]
- kotlin code : https://github.com/hojongs/spring-boot-example/tree/master/webflux/src/main/kotlin/com/hojong/springbootexample/webflux/echo

### Start Spring Data JPA
[Blog Post](https://medium.com/jongho-developer/start-spring-data-jpa-11daa42774d7?source=friends_link&sk=469737841545563107855b4e921b9bde)
- jpa module : https://github.com/hojongs/spring-boot-example/tree/master/jpa
- build script : https://github.com/hojongs/spring-boot-example/blob/master/jpa/build.gradle.kts
- kotlin code : https://github.com/hojongs/spring-boot-example/tree/master/jpa/src/main/kotlin/com/hojong/springbootexample/jpa/user

### Start Spring Boot Actuator
[Blog Post](https://medium.com/jongho-developer/start-spring-boot-actuator-b56c5dbf6a41?source=friends_link&sk=73d76aaa329d240a45c98c47caba0105)
- [webflux module][webflux]
- [build script][webflux build script]
- application properties : https://github.com/hojongs/spring-boot-example/blob/master/webflux/src/main/resources/application.yml

### Spring Boot + gRPC (and, my experience of gRPC)
[Blog Post](https://medium.com/jongho-developer/spring-boot-grpc-and-my-experience-of-grpc-fad4af471eb5?source=friends_link&sk=2964a8ed08ba3e77419346901e283e30)
- grpc module : https://github.com/hojongs/spring-boot-example/tree/master/grpc
- build script : https://github.com/hojongs/spring-boot-example/blob/master/grpc/build.gradle.kts
- kotlin code : https://github.com/hojongs/spring-boot-example/blob/master/grpc/src/main/kotlin/com/hojong/springbootexample/grpc/ui/GrpcGreeterService.kt

### Gradle publishing package to maven (GPR)
[Blog Post](https://medium.com/jongho-developer/gradle-publishing-package-to-maven-gpr-293565d75e91?source=friends_link&sk=89221c05f48778d53da1f4ff5140a827)
- publishing module : https://github.com/hojongs/spring-boot-example/tree/master/publishing
- build script : https://github.com/hojongs/spring-boot-example/blob/master/publishing/build.gradle.kts#L47-L64

### TBD: GraphQL
- graphql : https://github.com/hojongs/spring-boot-example/tree/master/graphql

## Run postgres docker container

```shell
docker-compose -f docker-compose.yml up
```

- You can access adminer by `localhost:8899` to connect to postgres
- Or, `docker exec -it my-postgres psql -p 5432 -U asdf -d asdf`

[webflux]: https://github.com/hojongs/spring-boot-example/tree/master/webflux
[webflux build script]: https://github.com/hojongs/spring-boot-example/blob/master/webflux/build.gradle.kts
