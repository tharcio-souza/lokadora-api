package br.com.tharcio.lokadoraapi.daos.request

data class PostUserRequest(
    var name: String,
    var email: String,
    var password: String

)