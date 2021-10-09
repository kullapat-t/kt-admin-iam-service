package com.kullapat.iam.infrastructure.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Swagger {
    @Bean
    fun openAPI(): OpenAPI? {
        return OpenAPI()
            .info(Info().title("KT Service")
                .description("KT Service - sample application")
                .version("1.0.0"))
    }
}
