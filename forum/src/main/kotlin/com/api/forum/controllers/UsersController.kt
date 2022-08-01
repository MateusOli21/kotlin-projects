package com.api.forum.controllers

import com.api.forum.dtos.CreateUserDto
import com.api.forum.models.User
import com.api.forum.services.UserService
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
@RequestMapping("/users")
class UsersController(
    private val userService: UserService
) {
    @GetMapping
    fun index(): ResponseEntity<List<User>> {
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.index())
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<User> {
        val user = this.userService.findById(id)

        return ResponseEntity.status(HttpStatus.OK).body(user)
    }

    @PostMapping
    fun create(
        @RequestBody @Valid
        body: CreateUserDto
    ): ResponseEntity<User> {
        val newUser = this.userService.create(body)

        return ResponseEntity.status(HttpStatus.CREATED).body(newUser)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        this.userService.findById(id)
        this.userService.delete(id)
    }
}
