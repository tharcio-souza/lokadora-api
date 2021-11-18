package br.com.tharcio.lokadoraapi.extensions

import br.com.tharcio.lokadoraapi.daos.request.PostUserRequest
import br.com.tharcio.lokadoraapi.daos.request.PutUserRequest
import br.com.tharcio.lokadoraapi.daos.response.UserResponse
import br.com.tharcio.lokadoraapi.enums.Profile
import br.com.tharcio.lokadoraapi.models.UserModel

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
        roles = setOf(Profile.USER)
    )
}
fun PutUserRequest.toUserModel(user: UserModel) : UserModel {
    return UserModel(
        id = user.id,
        name = this.name,
        email = this.email,
        password = user.password,
        roles = user.roles

    )
}