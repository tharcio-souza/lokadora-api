package br.com.tharcio.lokadoraapi.services

import br.com.tharcio.lokadoraapi.daos.request.PostUserRequest
import br.com.tharcio.lokadoraapi.daos.request.PutUserRequest
import br.com.tharcio.lokadoraapi.daos.response.UserResponse
import br.com.tharcio.lokadoraapi.extensions.toResponse
import br.com.tharcio.lokadoraapi.extensions.toUserModel
import br.com.tharcio.lokadoraapi.models.UserModel
import br.com.tharcio.lokadoraapi.repositories.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder
) {

    fun getAll(pageable: Pageable, name: String?): Page<UserResponse> {
        name?.let {
            return userRepository.findByNameContainingIgnoreCase(pageable, name).map { it.toResponse() }
        }
        return userRepository.findAll(pageable).map { it.toResponse() }
    }

    fun create(user: PostUserRequest) {
        user.password = passwordEncoder.encode(user.password)
        userRepository.save(user.toUserModel())
    }

    fun getById(id: Int): UserResponse {
        return userRepository.findById(id).orElseThrow { Exception() }.toResponse()
    }

    fun update(id: Int, user: PutUserRequest) {
        val userSaved = userRepository.findById(id).orElseThrow { Exception() }
        userRepository.save(user.toUserModel(userSaved))
    }

    fun delete(id: Int) {
        when (userRepository.existsById(id)) {
            true -> userRepository.deleteById(id)

            else -> throw Exception()
        }
    }


}