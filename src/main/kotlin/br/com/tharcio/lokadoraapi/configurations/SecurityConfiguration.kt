package br.com.tharcio.lokadoraapi.configurations

import br.com.tharcio.lokadoraapi.repositories.UserDAO
import br.com.tharcio.lokadoraapi.security.AuthenticationFilter
import br.com.tharcio.lokadoraapi.security.AuthorizationFilter
import br.com.tharcio.lokadoraapi.security.JwtUtil
import br.com.tharcio.lokadoraapi.services.impl.UserDetailsCustomService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val userDAO: UserDAO,
    private val userDetailsCustomService: UserDetailsCustomService,
    private val jwtUtil: JwtUtil
) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsCustomService).passwordEncoder(bCryptPasswordEncoder())
    }

    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable()

        http.authorizeRequests().antMatchers(HttpMethod.POST, "/users").permitAll().anyRequest().authenticated()

        http.addFilter(AuthenticationFilter(authenticationManager(), userDAO, jwtUtil))

        http.addFilter((AuthorizationFilter(authenticationManager(), userDetailsCustomService, jwtUtil)))

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }


    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }


}