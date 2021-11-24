package br.com.tharcio.lokadoraapi.services

import br.com.tharcio.lokadoraapi.enums.InternalErrorCodes
import br.com.tharcio.lokadoraapi.exceptions.NotFoundException
import br.com.tharcio.lokadoraapi.repositories.UserRepository
import br.com.tharcio.lokadoraapi.security.UserCustomDetails
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsCustomService(
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(id: String): UserDetails {
        val user = userRepository.findById(id.toInt()).orElseThrow {
            NotFoundException(
                InternalErrorCodes.LK_101.message.format(id),
                InternalErrorCodes.LK_101.code
            )
        }

        return UserCustomDetails(user)
    }
}