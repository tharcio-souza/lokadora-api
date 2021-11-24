package br.com.tharcio.lokadoraapi.enums

enum class InternalErrorCodes(val code: String, val message: String) {

    USER_NOT_FOUND("LK-101", "User from id %s not exists")

}