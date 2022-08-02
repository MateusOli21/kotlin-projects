package com.api.forum.mappers

import com.api.forum.dtos.ViewUserDto
import com.api.forum.models.User
import org.springframework.stereotype.Component

@Component
class UserToViewMapper: Mapper<User, ViewUserDto> {
    override fun map(t: User): ViewUserDto {
        return ViewUserDto(
            id= t.id,
            name = t.name,
            email = t.email,
            phone = t.phone
        )
    }
}