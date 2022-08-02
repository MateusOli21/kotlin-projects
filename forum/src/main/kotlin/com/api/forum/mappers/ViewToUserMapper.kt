package com.api.forum.mappers

import com.api.forum.dtos.ViewUserDto
import com.api.forum.models.User
import org.springframework.stereotype.Component

@Component
class ViewToUserMapper: Mapper<ViewUserDto, User> {
    override fun map(t: ViewUserDto): User {
        return User(
            id = t.id,
            name = t.name,
            email = t.email,
            phone = t.phone
        )
    }
}