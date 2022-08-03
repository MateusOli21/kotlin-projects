package com.api.forum.controllers

import com.api.forum.dtos.CreateTopicDto
import com.api.forum.dtos.ViewTopicDto
import com.api.forum.services.CoursesService
import com.api.forum.services.TopicsService
import javax.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/topics")
class TopicsController(
    private val topicsService: TopicsService,
    private val coursesService: CoursesService
) {

    @GetMapping
    fun index(
        @RequestParam(required = false) courseName: String?
    ): ResponseEntity<List<ViewTopicDto>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(this.topicsService.index(courseName))
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<ViewTopicDto> {
        val topic = this.topicsService.findById(id)

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(topic)
    }

    @PostMapping
    fun create(
        @RequestBody @Valid
        body: CreateTopicDto
    ): ResponseEntity<ViewTopicDto> {
        this.coursesService.findById(body.courseId)

        val newTopic = this.topicsService.create(body)

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(newTopic)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        this.topicsService.findById(id)
        this.topicsService.delete(id)
    }
}
