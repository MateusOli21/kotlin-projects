package com.api.forum.dtos

import javax.validation.constraints.NotEmpty

class CreateUserDto(
    @field:NotEmpty val name: String,
    @field:NotEmpty val email: String,
    @field:NotEmpty val phone: String
)
