package io.redspark.candidatos.config.security

import com.nimbusds.jwt.JWTClaimsSet
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class AuthenticationToken(
    private val token: String,
    private val details: JWTClaimsSet,
    private val authorities: List<GrantedAuthority> = listOf()
) : AbstractAuthenticationToken(authorities) {

    init {
        setDetails(details)
        isAuthenticated = true
    }

    override fun getCredentials(): Any {
        return token
    }

    override fun getPrincipal(): Any {
        return details
    }

    override fun getName(): String {
        return token
    }

}
