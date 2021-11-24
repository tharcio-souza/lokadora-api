package br.com.tharcio.lokadoraapi.enums

enum class InternalErrorCodes(val code: String, val message: String) {

    LK_101("LK-101", "User from id %s not exists"),
    LK_102("LK-102", "No such user in the database")



}