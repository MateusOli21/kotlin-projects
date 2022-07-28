package com.api.forum.services

import com.api.forum.exceptions.NotFoundException
import com.api.forum.models.Course
import org.springframework.stereotype.Service

@Service
class CoursesService(var courses: List<Course>) {
    init {
        val kotlinCourse = Course(1, "Kotlin", "Programação")
        val javaCourse = Course(2, "Java", "Programação")

        this.courses = listOf(kotlinCourse, javaCourse)
    }

    fun index(): List<Course>{
        return  this.courses
    }

    fun findById(id: Long): Course?{
        return this.courses.stream()
            .filter { course: Course -> course.id ==id  }
            .findFirst()
            .orElseThrow { NotFoundException("Curso não encontrado") }
    }

}