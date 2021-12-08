package br.com.tharcio.lokadoraapi.dtos.response

data class UserResponse(
    val id: Int? = null,
    val name: String,
    val email: String,
    val role: String
)