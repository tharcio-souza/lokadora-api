package br.com.tharcio.lokadoraapi.extensions

import br.com.tharcio.lokadoraapi.dtos.request.PostUserRequest
import br.com.tharcio.lokadoraapi.dtos.request.PutUserRequest
import br.com.tharcio.lokadoraapi.dtos.response.PageResponse
import br.com.tharcio.lokadoraapi.dtos.response.UserResponse
import br.com.tharcio.lokadoraapi.enums.Roles
import br.com.tharcio.lokadoraapi.entities.User
import org.springframework.data.domain.Page

fun <T> Page<T>.toPageResponse(): PageResponse<T> {

    return PageResponse(
        elements = this.content,
        currentPage = this.number,
        totalPages = this.totalPages,
        totalElements = this.totalElements
    )
}

fun User.toResponse(): UserResponse {
    return UserResponse(
        id = this.id,
        name = this.name,
        email = this.email,
        role = this.roles.toString()
    )
}

fun PostUserRequest.toUserModel(): User {
    return User(
        name = this.name,
        email = this.email,
        password = this.password,
        roles = setOf(Roles.USER)
    )
}

fun PutUserRequest.toUserModel(user: User): User {
    return User(
        id = user.id,
        name = this.name,
        email = this.email,
        password = user.password,
        roles = user.roles

    )
}