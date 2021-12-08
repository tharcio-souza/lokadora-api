package br.com.tharcio.lokadoraapi.utils

import br.com.tharcio.lokadoraapi.enums.Roles
import br.com.tharcio.lokadoraapi.entities.User
import br.com.tharcio.lokadoraapi.repositories.UserDAO
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class DBInit(
    private val userRepository: UserDAO,
    private val passwordEncoder: BCryptPasswordEncoder
) {

    private val admEmail = "admin@lokadora.org.br"

    @PostConstruct
    fun createUserAdmin() {
        if (!userRepository.existsByEmail(admEmail)) {
            val admin = User(
                name = "admin",
                email = admEmail,
                password = passwordEncoder.encode("admin"),
                roles = setOf(Roles.ADMIN, Roles.USER)
            )
            userRepository.save(admin)
        }
    }
}