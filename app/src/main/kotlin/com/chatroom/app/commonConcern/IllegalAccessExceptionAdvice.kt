package com.chatroom.app.commonConcern

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class IllegalAccessExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(IllegalAccessException::class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    fun illegalAccessExceptionHandler(
        exception: IllegalAccessException
    ) = exception.message
}
