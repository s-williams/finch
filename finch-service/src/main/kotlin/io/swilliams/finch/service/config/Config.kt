package io.swilliams.finch.service.config

import io.swilliams.finch.ipapi.IPAPIApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Config {

    @Bean
    fun ipApiClient() = IPAPIApi()
}
