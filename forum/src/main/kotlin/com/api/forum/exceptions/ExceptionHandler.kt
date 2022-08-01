package com.api.forum.exceptions

import com.api.forum.dtos.ErrorExceptionDto
import javax.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFound(
        exception: NotFoundException,
        request: HttpServletRequest
    ): ErrorExceptionDto {
        return ErrorExceptionDto(
            status = HttpStatus.NOT_FOUND.value(),
            error = HttpStatus.NOT_FOUND.name,
            message = exception.message,
            path = request.servletPath
        )
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleServerError(
        exception: NotFoundException,
        request: HttpServletRequest
    ): ErrorExceptionDto {
        return ErrorExceptionDto(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error = HttpStatus.INTERNAL_SERVER_ERROR.name,
            message = exception.message,
            path = request.servletPath
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleBadRequest(
        exception: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): ErrorExceptionDto {
        val errorMessages = HashMap<String, String?>()

        exception.bindingResult.fieldErrors.forEach {
                err ->
            errorMessages.put(err.field, err.defaultMessage)
        }

        return ErrorExceptionDto(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.name,
            message = errorMessages.toString(),
            path = request.servletPath
        )
    }
}
