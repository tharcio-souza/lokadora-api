package br.com.tharcio.lokadoraapi.daos.response

data class UserResponse(
    var id: Int? = null,
    var name: String,
    var email: String,
    var role: String

)