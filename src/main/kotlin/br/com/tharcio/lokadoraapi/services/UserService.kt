package br.com.tharcio.lokadoraapi.services

import br.com.tharcio.lokadoraapi.dtos.request.PostUserRequest
import br.com.tharcio.lokadoraapi.dtos.request.PutUserRequest
import br.com.tharcio.lokadoraapi.dtos.response.UserResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface UserService {

    fun getAll(pageable: Pageable, name: String?): Page<UserResponse>
    fun create(user: PostUserRequest)
    fun getById(id: Int): UserResponse
    fun update(id: Int, user: PutUserRequest)
    fun delete(id: Int)
    fun emailIsAvaible(value: String): Boolean

}