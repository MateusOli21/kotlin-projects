package com.api.forum.services

import com.api.forum.dtos.CreateTopicDto
import com.api.forum.exceptions.NotFoundException
import com.api.forum.mappers.CreateToTopicMapper
import com.api.forum.models.Topic
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class TopicsService(
    private var topics: MutableList<Topic> = ArrayList(),
    private val createToTopicMapper: CreateToTopicMapper
) {

    fun list(): List<Topic>{
        return this.topics
    }

    fun findById(id: UUID): Topic? {
        return topics.stream()
            .filter { topic -> topic.id == id }
            .findFirst()
            .orElseThrow { NotFoundException("Tópico não encontrado") }
    }

    fun create(topicDto: CreateTopicDto): Topic{
        val newTopic: Topic = this.createToTopicMapper.map(topicDto)
        newTopic.id = UUID.randomUUID()

        this.topics.add(newTopic)

        return newTopic
    }

    fun delete(id: UUID) {
        val topic = this.findById(id)

        this.topics.remove(topic)
    }
}