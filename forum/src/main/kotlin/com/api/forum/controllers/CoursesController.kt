package com.api.forum.controllers

import com.api.forum.models.Course
import com.api.forum.services.CoursesService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/courses")
class CoursesController(
    private val coursesService: CoursesService
) {

    @GetMapping
    fun index(): List<Course>{
       return this.coursesService.index()
    }
}