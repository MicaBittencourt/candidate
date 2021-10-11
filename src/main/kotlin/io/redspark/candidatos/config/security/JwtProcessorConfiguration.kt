package io.redspark.candidatos.config.security

import com.nimbusds.jwt.proc.ConfigurableJWTProcessor
import io.redspark.candidatos.config.logger.LoggerDelegate
import io.redspark.candidatos.database.repositories.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JwtProcessorConfiguration(
    private val userRepository: UserRepository
) {

    private val logger by LoggerDelegate()

    @Bean
    fun configurableJWTProcessor(): ConfigurableJWTProcessor<*> = localProcessor()

    private fun localProcessor(): ConfigurableJWTProcessor<*> {
        logger.info("Security: Use local JWT processor")
        return LocalJwtProcessor(userRepository)
    }

}
