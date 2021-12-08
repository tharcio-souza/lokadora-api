package br.com.tharcio.lokadoraapi.dtos.response

data class ErrorResponse(
    var httpCode: Int,
    var message: String,
    var internalCode: String,
    var errors: List<FieldErrorResponse>?
)
