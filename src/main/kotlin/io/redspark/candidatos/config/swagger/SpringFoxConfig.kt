package io.redspark.candidatos.config.swagger

import org.springframework.context.annotation.Configuration
import org.springframework.util.StringUtils
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


class SpringFoxConfig(
    val baseUrl: String

) : WebMvcConfigurer {

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        val baseUrl: String = StringUtils.trimTrailingCharacter(baseUrl, '/')
        registry.addResourceHandler("$baseUrl/swagger-ui/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
            .resourceChain(false)
    }

    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addViewController("$baseUrl/swagger-ui/")
            .setViewName("forward:$baseUrl/swagger-ui/index.html")
    }
}


