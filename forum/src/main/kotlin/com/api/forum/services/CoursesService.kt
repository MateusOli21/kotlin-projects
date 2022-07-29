package com.api.forum.services

import com.api.forum.dtos.CreateCourseDto
import com.api.forum.exceptions.NotFoundException
import com.api.forum.models.Course
import com.api.forum.repositories.CourseRepository
import org.springframework.stereotype.Service

@Service
class CoursesService(
    private val courseRepository: CourseRepository
) {

    fun index(): List<Course>{
        return this.courseRepository.findAll()
    }

    fun create(createCourseDto: CreateCourseDto): Course {
        val newCourse = Course(name = createCourseDto.name, category = createCourseDto.category)

        return this.courseRepository.save(newCourse)
    }

    fun findById(id: Long): Course{
        return this.courseRepository
            .findById(id)
            .orElseThrow { NotFoundException("Curso não encontrado") }
    }

    fun delete(id: Long) {
        this.findById(id)

        this.courseRepository.deleteById(id)
    }
}