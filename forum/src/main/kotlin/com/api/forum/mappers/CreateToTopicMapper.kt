package com.api.forum.mappers

import com.api.forum.dtos.CreateTopicDto
import com.api.forum.models.Topic
import com.api.forum.services.CoursesService
import com.api.forum.services.UserService
import org.springframework.stereotype.Component

@Component
class CreateToTopicMapper(
    private val coursesService: CoursesService,
    private val userService: UserService,
    private val viewToUserMapper: ViewToUserMapper
) : Mapper<CreateTopicDto, Topic> {
    override fun map(t: CreateTopicDto): Topic {
        val authorView = this.userService.findById(t.authorId)

        return Topic(
            title = t.title,
            message = t.message,
            course = this.coursesService.findById(t.courseId),
            author = this.viewToUserMapper.map(authorView)
        )
    }
}
