package com.juso.batch.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationConfig {

    @Bean
    fun objectMapper(): ObjectMapper {
        return ObjectMapper()
    }

    @Bean
    fun log(): Logger {
        return LoggerFactory.getLogger(this::class.java)
    }
}