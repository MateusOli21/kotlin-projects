package com.api.forum.mappers

import com.api.forum.dtos.CreateTopicDto
import com.api.forum.models.Topic
import org.springframework.stereotype.Component

@Component
class CreateToTopicMapper: Mapper<CreateTopicDto, Topic> {
    override fun map(t: CreateTopicDto): Topic {
        return Topic(
            title = t.title,
            message = t.message,
            courseId = t.courseId,
            authorId = t.authorId
        )
    }
}