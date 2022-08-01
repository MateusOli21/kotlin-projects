package com.api.forum.repositories

import com.api.forum.models.Topic
import org.springframework.data.jpa.repository.JpaRepository

interface TopicRepository : JpaRepository<Topic, Long> {
    fun findByCourseNameIgnoreCase(courseName: String): List<Topic>
}
