package com.api.forum.controllers

import com.api.forum.dtos.CreateCourseDto
import com.api.forum.models.Course
import com.api.forum.services.CoursesService
import javax.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/courses")
class CoursesController(
    private val coursesService: CoursesService
) {

    @GetMapping
    fun index(): List<Course> {
        return this.coursesService.index()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Course> {
        val course = this.coursesService.findById(id)

        return ResponseEntity.status(HttpStatus.OK).body(course)
    }

    @PostMapping
    fun create(
        @RequestBody @Valid
        body: CreateCourseDto
    ): ResponseEntity<Course> {
        val course = this.coursesService.create(body)

        return ResponseEntity.status(HttpStatus.CREATED).body(course)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        this.findById(id)
        this.coursesService.delete(id)
    }
}
