package io.redspark.candidatos.config.security

import com.nimbusds.jose.proc.SecurityContext
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthFilter(
    private val processorJWT: ConfigurableJWTProcessor<SecurityContext>,
    private val authManager: AuthenticationManager,
    private val authoritiesKey: String
) : BasicAuthenticationFilter(authManager) {

    private val securityHeader = "Authorization"
    private val tokenType = "Bearer"

    override fun doFilterInternal(
        req: HttpServletRequest,
        res: HttpServletResponse,
        chain: FilterChain
    ) {
        try {
            val token = extractToken(req.getHeader(securityHeader))
            val authentication = extractAuthentication(token, req.requestURI)
            SecurityContextHolder.getContext().authentication = authentication
        } catch (accessDeniedException: AccessDeniedException) {
            val message = accessDeniedException.message ?: "Unauthorized"
            logger.error("Access denied: $message")
        }
        chain.doFilter(req, res)
    }

    private fun extractToken(header: String?): String? {
        return header?.let {
            val headers = it.split("$tokenType ")
            return if (headers.isEmpty()) null else headers.lastOrNull()
        }
    }

    @Throws(AccessDeniedException::class)
    private fun extractAuthentication(token: String?, path: String): AuthenticationToken? = if (token == null) {
        null
    } else {
        try {
            val claims = processorJWT.process(token, null)
            val grantedAuthorities = claims.getStringListClaim(authoritiesKey)
                .map { SimpleGrantedAuthority(it) }

            AuthenticationToken(token, claims, grantedAuthorities)
        } catch (exception: Exception) {
            throw AccessDeniedException("${exception.javaClass.simpleName} (${exception.message ?: "No message"})")
        }
    }

}
