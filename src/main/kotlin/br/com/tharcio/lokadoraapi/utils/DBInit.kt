package br.com.tharcio.lokadoraapi.utils

import br.com.tharcio.lokadoraapi.enums.Roles
import br.com.tharcio.lokadoraapi.models.UserModel
import br.com.tharcio.lokadoraapi.repositories.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class DBInit(
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder

) {


    @PostConstruct
    fun createUserAdmin() {

        if (!userRepository.existsByEmail("admin@lokadora.org.br")) {
            val admin = UserModel(
                name = "admin",
                email = "admin@lokadora.org.br",
                password = passwordEncoder.encode("admin"),
                roles = setOf(Roles.ADMIN, Roles.USER)
            )
            userRepository.save(admin)
        }

    }
}