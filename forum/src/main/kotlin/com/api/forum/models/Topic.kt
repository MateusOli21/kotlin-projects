package com.api.forum.models

import java.time.LocalDateTime
import java.util.UUID

data class Topic(
    var id: UUID? = null,
    val title: String,
    val message: String,
    val courseId: Long,
    val authorId: Long,
    val answers: List<Answer> = ArrayList(),
    val created_at: LocalDateTime = LocalDateTime.now(),
)