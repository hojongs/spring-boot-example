package com.hojong.springbootexample.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory

open class LoggerProvider {
    val logger: Logger = LoggerFactory.getLogger(this::class.java)!!
}
