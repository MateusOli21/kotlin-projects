package com.api.forum.repositories

import com.api.forum.models.Course
import org.springframework.data.jpa.repository.JpaRepository

interface CourseRepository: JpaRepository<Course, Long>