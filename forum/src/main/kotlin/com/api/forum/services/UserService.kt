package com.api.forum.services

import com.api.forum.dtos.CreateUserDto
import com.api.forum.dtos.ViewUserDto
import com.api.forum.exceptions.NotFoundException
import com.api.forum.mappers.UserToViewMapper
import com.api.forum.models.User
import com.api.forum.repositories.UserRepository
import java.util.stream.Collectors
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val userToViewMapper: UserToViewMapper
) : UserDetailsService {
    fun index(): List<ViewUserDto> {
        return this.userRepository.findAll()
            .stream().map { user -> this.userToViewMapper.map(user) }
            .collect(Collectors.toList())
    }

    fun findById(id: Long): ViewUserDto {
        val user = this.userRepository
            .findById(id)
            .orElseThrow { NotFoundException("Não foi possível encontrar usuário") }

        return this.userToViewMapper.map(user)
    }

    fun create(data: CreateUserDto): ViewUserDto {
        val passwordEncoder = BCryptPasswordEncoder()

        val passwordHash = passwordEncoder.encode(data.password)

        val newUser = User(
            name = data.name,
            email = data.email,
            phone = data.phone,
            password = passwordHash
        )

        this.userRepository.save(newUser)

        return this.userToViewMapper.map(newUser)
    }

    fun delete(id: Long) {
        this.findById(id)

        this.userRepository.deleteById(id)
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val user =
            this.userRepository.findByEmail(username) ?: throw NotFoundException("Não foi possível encontar usuário")

        return UserDetail(user)
    }
}
