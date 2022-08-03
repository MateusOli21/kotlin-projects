package com.api.forum.services

import com.api.forum.dtos.CreateTopicDto
import com.api.forum.dtos.ViewTopicDto
import com.api.forum.exceptions.NotFoundException
import com.api.forum.mappers.CreateToTopicMapper
import com.api.forum.mappers.TopicToViewMapper
import com.api.forum.models.Topic
import com.api.forum.repositories.TopicRepository
import java.util.stream.Collectors
import org.springframework.stereotype.Service

@Service
class TopicsService(
    private val createToTopicMapper: CreateToTopicMapper,
    private val topicRepository: TopicRepository,
    private val topicToViewMapper: TopicToViewMapper
) {

    fun index(courseName: String?): List<ViewTopicDto> {
        val tops = if (courseName !== null) {
            this.topicRepository.findByCourseNameIgnoreCase(courseName)
        } else {
            this.topicRepository.findAll()
        }

        return tops.stream()
            .map { top -> this.topicToViewMapper.map(top) }
            .collect(Collectors.toList())
    }

    fun findById(id: Long): ViewTopicDto {
        val topic = this.topicRepository
            .findById(id)
            .orElseThrow { NotFoundException("Não foi possível encontrar tópico") }

        return this.topicToViewMapper.map(topic)
    }

    fun create(topicDto: CreateTopicDto): ViewTopicDto {
        val newTopic: Topic = this.createToTopicMapper.map(topicDto)

        this.topicRepository.save(newTopic)

        return this.topicToViewMapper.map(newTopic)
    }

    fun delete(id: Long) {
        this.findById(id)
        this.topicRepository.deleteById(id)
    }
}
