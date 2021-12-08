package br.com.tharcio.lokadoraapi.services.impl

import br.com.tharcio.lokadoraapi.dtos.request.PostUserRequest
import br.com.tharcio.lokadoraapi.dtos.request.PutUserRequest
import br.com.tharcio.lokadoraapi.dtos.response.UserResponse
import br.com.tharcio.lokadoraapi.enums.InternalErrorCodes
import br.com.tharcio.lokadoraapi.exceptions.NotFoundException
import br.com.tharcio.lokadoraapi.extensions.toResponse
import br.com.tharcio.lokadoraapi.extensions.toUserModel
import br.com.tharcio.lokadoraapi.repositories.UserDAO
import br.com.tharcio.lokadoraapi.services.UserService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserDAO,
    private val passwordEncoder: BCryptPasswordEncoder
) : UserService {

    override fun getAll(pageable: Pageable, name: String?): Page<UserResponse> {

        name?.let {
            val pageResponse = userRepository.findByNameContainingIgnoreCase(pageable, name).map { it.toResponse() }
            return verifyIfPageHasUsers(pageResponse)
        }
        val pageResponse = userRepository.findAll(pageable).map { it.toResponse() }
        return verifyIfPageHasUsers(pageResponse)
    }


    override fun create(user: PostUserRequest) {
        user.password = passwordEncoder.encode(user.password)
        userRepository.save(user.toUserModel())
    }

    override fun getById(id: Int): UserResponse {
        return userRepository.findById(id).orElseThrow {
            NotFoundException(
                InternalErrorCodes.LK_101.message.format(id),
                InternalErrorCodes.LK_101.code
            )
        }.toResponse()
    }

    override fun update(id: Int, user: PutUserRequest) {
        val userSaved = userRepository.findById(id).orElseThrow {
            NotFoundException(
                InternalErrorCodes.LK_101.message.format(id),
                InternalErrorCodes.LK_101.code
            )
        }
        userRepository.save(user.toUserModel(userSaved))
    }

    override fun delete(id: Int) {
        when (userRepository.existsById(id)) {
            true -> userRepository.deleteById(id)

            else -> throw NotFoundException(
                InternalErrorCodes.LK_101.message.format(id),
                InternalErrorCodes.LK_101.code
            )
        }
    }


    override fun emailIsAvaible(value: String): Boolean {
        return !userRepository.existsByEmail(value)
    }

    private fun verifyIfPageHasUsers(pageResponse: Page<UserResponse>): Page<UserResponse> {
        when (pageResponse.content.isEmpty()) {
            true -> throw NotFoundException(InternalErrorCodes.LK_102.message, InternalErrorCodes.LK_102.code)
            false -> return pageResponse
        }
    }
}
