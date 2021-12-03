package br.com.tharcio.lokadoraapi.exceptions

import br.com.tharcio.lokadoraapi.daos.response.ErrorResponse
import br.com.tharcio.lokadoraapi.daos.response.FieldErrorResponse
import br.com.tharcio.lokadoraapi.enums.InternalErrorCodes
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(ex: NotFoundException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse(
            httpCode = HttpStatus.NOT_FOUND.value(),
            message = ex.message,
            internalCode = ex.internalErrorCode,
            errors = null
        )
        return ResponseEntity(response, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException, request: WebRequest) : ResponseEntity<ErrorResponse> {
        val response = ErrorResponse(
            httpCode = HttpStatus.UNPROCESSABLE_ENTITY.value(),
            message = InternalErrorCodes.LK_001.message,
            internalCode = InternalErrorCodes.LK_001.code,
            errors = ex.bindingResult.fieldErrors.map { FieldErrorResponse(it.defaultMessage ?: "invalid", it.field) }
        )
        return ResponseEntity(response, HttpStatus.UNPROCESSABLE_ENTITY)
    }
}