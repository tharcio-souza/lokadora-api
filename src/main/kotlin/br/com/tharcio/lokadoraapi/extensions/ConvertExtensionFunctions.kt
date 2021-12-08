package br.com.tharcio.lokadoraapi.extensions

import br.com.tharcio.lokadoraapi.daos.request.PostUserRequest
import br.com.tharcio.lokadoraapi.daos.request.PutUserRequest
import br.com.tharcio.lokadoraapi.daos.response.PageResponse
import br.com.tharcio.lokadoraapi.daos.response.UserResponse
import br.com.tharcio.lokadoraapi.enums.Roles
import br.com.tharcio.lokadoraapi.models.UserModel
import org.springframework.data.domain.Page

fun <T> Page<T>.toPageResponse(): PageResponse<T> {

    return PageResponse(
        elements = this.content,
        currentPage = this.number,
        totalPages = this.totalPages,
        totalElements = this.totalElements
    )
}

fun UserModel.toResponse(): UserResponse {
    return UserResponse(
        id = this.id,
        name = this.name,
        email = this.email,
        role = this.roles.toString()
    )
}

fun PostUserRequest.toUserModel(): UserModel {
    return UserModel(
        name = this.name,
        email = this.email,
        password = this.password,
        roles = setOf(Roles.USER)
    )
}

fun PutUserRequest.toUserModel(user: UserModel): UserModel {
    return UserModel(
        id = user.id,
        name = this.name,
        email = this.email,
        password = user.password,
        roles = user.roles

    )
}