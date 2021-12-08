package br.com.tharcio.lokadoraapi.dtos.request

data class LoginRequest (
    val email: String,
    val password: String
)