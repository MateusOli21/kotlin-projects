package com.api.forum.services

import com.api.forum.dtos.CreateUserDto
import com.api.forum.exceptions.NotFoundException
import com.api.forum.models.User
import com.api.forum.repositories.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) : UserDetailsService {
    fun index(): List<User> {
        return this.userRepository.findAll()
    }

    fun findById(id: Long): User {
        return this.userRepository
            .findById(id)
            .orElseThrow { NotFoundException("Não foi possível encontrar usuário") }
    }

    fun create(data: CreateUserDto): User {
        val passwordEncoder = BCryptPasswordEncoder()

        val passwordHash = passwordEncoder.encode(data.password)

        val newUser = User(
            name = data.name,
            email = data.email,
            phone = data.phone,
            password = passwordHash
        )

        this.userRepository.save(newUser)

        return newUser
    }

    fun delete(id: Long) {
        val user = this.findById(id)

        this.userRepository.delete(user)
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val user =
            this.userRepository.findByEmail(username) ?: throw NotFoundException("Não foi possível encontar usuário")

        return UserDetail(user)
    }
}
