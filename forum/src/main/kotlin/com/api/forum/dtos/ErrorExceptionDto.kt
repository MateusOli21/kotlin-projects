package com.api.forum.dtos

import java.time.LocalDateTime

class ErrorExceptionDto(
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val error: String,
    val message: String?,
    val path: String
)
