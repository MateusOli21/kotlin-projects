package com.api.forum.repositories

import com.api.forum.models.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long>