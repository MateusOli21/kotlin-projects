package com.api.forum.dtos

import javax.validation.constraints.NotEmpty

data class CreateCourseDto(
    @field:NotEmpty val name: String,
    @field:NotEmpty val category: String
)
