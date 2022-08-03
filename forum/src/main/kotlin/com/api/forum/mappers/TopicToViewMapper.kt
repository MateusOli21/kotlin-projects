package com.api.forum.mappers

import com.api.forum.dtos.ViewTopicDto
import com.api.forum.models.Topic
import org.springframework.stereotype.Component

@Component
class TopicToViewMapper(
    private val userToViewMapper: UserToViewMapper
) : Mapper<Topic, ViewTopicDto> {
    override fun map(t: Topic): ViewTopicDto {
        return ViewTopicDto(
            id = t.id,
            title = t.title,
            message = t.message,
            course = t.course,
            author = this.userToViewMapper.map(t.author),
            answers = t.answers,
            created_at = t.created_at
        )
    }
}
