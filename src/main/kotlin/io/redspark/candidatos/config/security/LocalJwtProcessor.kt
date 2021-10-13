package io.redspark.candidatos.config.security

import com.nimbusds.jose.proc.SecurityContext
import com.nimbusds.jwt.JWTClaimsSet
import com.nimbusds.jwt.proc.DefaultJWTProcessor
import io.redspark.candidatos.database.repositories.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class LocalJwtProcessor (
    private val userRepository: UserRepository
) : DefaultJWTProcessor<SecurityContext>() {

    override fun process(jwtString: String?, context: SecurityContext?): JWTClaimsSet {
        if (jwtString == null) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }

        val user = userRepository.findFirstByEmail(jwtString) ?: throw ResponseStatusException(HttpStatus.UNAUTHORIZED)

        val claims = user.permissionList
            .map { it.permission.name }

        if (claims.isEmpty()) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }

        return JWTClaimsSet.Builder()
            .claim("authorities", claims)
            .audience(jwtString)
            .issuer(jwtString)
            .build()
    }

}
