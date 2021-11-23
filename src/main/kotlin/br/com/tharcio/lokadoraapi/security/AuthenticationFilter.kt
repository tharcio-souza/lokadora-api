package br.com.tharcio.lokadoraapi.security

import br.com.tharcio.lokadoraapi.daos.request.LoginRequest
import br.com.tharcio.lokadoraapi.repositories.UserRepository
import br.com.tharcio.lokadoraapi.services.UserService
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationFilter(
    authenticationManager: AuthenticationManager,
    private val userRepository: UserRepository,
    private val jwtUtil: JwtUtil

) : UsernamePasswordAuthenticationFilter(authenticationManager) {

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        try {
            val loginRequest = jacksonObjectMapper().readValue(request.inputStream, LoginRequest::class.java)
            val userId = userRepository.findByEmail(loginRequest.email).orElseThrow { Exception() }.id
            val authenticationToken = UsernamePasswordAuthenticationToken(userId, loginRequest.password)
            return authenticationManager.authenticate(authenticationToken)
        } catch (ex: Exception) {
            throw Exception()
        }
    }

    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
        authResult: Authentication
    ) {
        val id = (authResult.principal as UserCustomDetails).user.id!!

        val token = jwtUtil.generateToken(id)
        response.addHeader("Authorization", "Bearer $token")
    }
}