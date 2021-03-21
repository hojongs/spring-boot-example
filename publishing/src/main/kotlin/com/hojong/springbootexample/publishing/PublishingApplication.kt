package com.hojong.springbootexample.publishing

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PublishingApplication

fun main(args: Array<String>) {
	runApplication<PublishingApplication>(*args)
}
