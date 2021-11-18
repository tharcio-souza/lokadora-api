package br.com.tharcio.lokadoraapi.daos.request

data class LoginRequest (
    val email: String,
    val password: String
)