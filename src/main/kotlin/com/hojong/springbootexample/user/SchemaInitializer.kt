package com.hojong.springbootexample.user

import org.springframework.core.io.ClassPathResource

import io.r2dbc.spi.ConnectionFactory
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class SchemaInitializer(
    private val connectionFactory: ConnectionFactory
) : ConnectionFactoryInitializer() {

    @PostConstruct
    fun init() {
        setConnectionFactory(connectionFactory)
        setDatabasePopulator(ResourceDatabasePopulator(ClassPathResource("schema.sql")))
    }
}
