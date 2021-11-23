package br.com.tharcio.lokadoraapi.security

import br.com.tharcio.lokadoraapi.services.UserDetailsCustomService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthorizationFilter(
    authenticationManager: AuthenticationManager,
    private val userDetailsCustomService: UserDetailsCustomService,
    private val jwtUtil: JwtUtil
) : BasicAuthenticationFilter(authenticationManager) {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val authorization = request.getHeader("Authorization")

        if (authorization != null && authorization.startsWith("Bearer ")) {
            val auth = getAuthentication(authorization.substringAfter(" "))
            SecurityContextHolder.getContext().authentication = auth
        }

        chain.doFilter(request, response)

    }

    private fun getAuthentication(token: String): UsernamePasswordAuthenticationToken {
        if (!jwtUtil.isValidToken(token)) {
            throw Exception()
        }
        val subject = jwtUtil.getSubject(token)
        val user = userDetailsCustomService.loadUserByUsername(subject)
        return UsernamePasswordAuthenticationToken(subject, null, user.authorities)
    }
}