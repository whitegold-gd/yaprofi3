package com.whitegold.yaprofi3.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Configuration

@Configuration
@OpenAPIDefinition
class SwaggerConfig {
    fun config(): OpenAPI{
        return OpenAPI().info(Info()
            .version("2.0.2")
            .description("spring docs")
            .title("Spring docs"))
    }
}