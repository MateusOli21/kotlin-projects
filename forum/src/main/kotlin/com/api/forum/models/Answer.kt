package com.api.forum.models

import java.time.LocalDateTime

data class Answer(
    val id: Long? = null,
    val message: String,
    val author: User,
    val topic: Topic,
    val solved: Boolean,
    val created_at: LocalDateTime = LocalDateTime.now()
) {
}