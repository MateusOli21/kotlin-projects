package com.api.forum.services

import com.api.forum.dtos.CreateTopicDto
import com.api.forum.exceptions.NotFoundException
import com.api.forum.mappers.CreateToTopicMapper
import com.api.forum.models.Topic
import com.api.forum.repositories.TopicRepository
import org.springframework.stereotype.Service

@Service
class TopicsService(
    private val createToTopicMapper: CreateToTopicMapper,
    private val topicRepository: TopicRepository
) {

    fun index(courseName: String?): List<Topic> {
        if (courseName !== null) return this.topicRepository.findByCourseNameIgnoreCase(courseName)

        return this.topicRepository.findAll()
    }

    fun findById(id: Long): Topic {
        return this.topicRepository
            .findById(id)
            .orElseThrow { NotFoundException("Não foi possível encontrar tópico") }
    }

    fun create(topicDto: CreateTopicDto): Topic {
        val newTopic: Topic = this.createToTopicMapper.map(topicDto)

        this.topicRepository.save(newTopic)

        return newTopic
    }

    fun delete(id: Long) {
        val topic = this.findById(id)

        this.topicRepository.delete(topic)
    }
}
