package io.swilliams.finch.service.config

import io.swilliams.finch.ipapi.IPAPIApi
import io.swilliams.finch.service.dao.RequestRepository
import io.swilliams.finch.service.security.FinchInterceptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig: WebMvcConfigurer {

    @Autowired
    lateinit var requestRepository: RequestRepository

    @Autowired
    lateinit var ipApiClient: IPAPIApi

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(FinchInterceptor(requestRepository, ipApiClient))
    }
}