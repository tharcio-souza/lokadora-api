package br.com.tharcio.lokadoraapi.exceptions

class NotFoundException(
    override val message: String,
    val internalErrorCode: String
): Exception() {
}