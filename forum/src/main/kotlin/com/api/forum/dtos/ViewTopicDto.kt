package com.api.forum.dtos

import com.api.forum.models.Answer
import com.api.forum.models.Course
import java.time.LocalDateTime

data class ViewTopicDto(
    val id: Long?,
    val title: String,
    val message: String,
    val course: Course,
    val author: ViewUserDto,
    val answers: List<Answer>,
    val created_at: LocalDateTime
)
