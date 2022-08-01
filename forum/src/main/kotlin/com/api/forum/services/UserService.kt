package com.api.forum.services

import com.api.forum.dtos.CreateUserDto
import com.api.forum.exceptions.NotFoundException
import com.api.forum.models.User
import com.api.forum.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun index(): List<User> {
        return this.userRepository.findAll()
    }

    fun findById(id: Long): User {
        return this.userRepository
            .findById(id)
            .orElseThrow { NotFoundException("Não foi possível encontrar usuário") }
    }

    fun create(data: CreateUserDto): User {
        val newUser = User(
            name = data.name,
            email = data.email,
            phone = data.phone
        )

        this.userRepository.save(newUser)

        return newUser
    }

    fun delete(id: Long) {
        val user = this.findById(id)

        this.userRepository.delete(user)
    }
}
