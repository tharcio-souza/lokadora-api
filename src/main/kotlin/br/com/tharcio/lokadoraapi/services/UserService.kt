package br.com.tharcio.lokadoraapi.services

import br.com.tharcio.lokadoraapi.daos.request.PostUserRequest
import br.com.tharcio.lokadoraapi.daos.request.PutUserRequest
import br.com.tharcio.lokadoraapi.daos.response.UserResponse
import br.com.tharcio.lokadoraapi.extensions.toResponse
import br.com.tharcio.lokadoraapi.extensions.toUserModel
import br.com.tharcio.lokadoraapi.models.UserModel
import br.com.tharcio.lokadoraapi.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun getAll(): List<UserResponse> {
        return userRepository.findAll().map { it.toResponse() }
    }

    fun create(user: PostUserRequest) {
        //Encriptar o password aqui
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