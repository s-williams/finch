package io.swilliams.finch.service.controllers

import io.swilliams.finch.api.model.ErrorResponse
import io.swilliams.finch.service.exceptions.FinchSecurityException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class FinchExceptionHandler {

    @ExceptionHandler(FinchSecurityException::class)
    fun handleException(e: FinchSecurityException, request: WebRequest): ResponseEntity<Any> {
        return ResponseEntity(
            ErrorResponse(
                code = HttpStatus.FORBIDDEN.value(),
                status = HttpStatus.FORBIDDEN.name,
                message = e.message
            ),
            HttpStatus.FORBIDDEN,
        )
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception, request: WebRequest): ResponseEntity<Any> {
        return ResponseEntity(
            ErrorResponse(
                code = HttpStatus.BAD_REQUEST.value(),
                status = HttpStatus.BAD_REQUEST.name,
                message = e.message
            ),
            HttpStatus.BAD_REQUEST,
        )
    }
}