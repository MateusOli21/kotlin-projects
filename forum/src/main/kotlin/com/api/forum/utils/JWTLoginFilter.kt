package com.api.forum.utils

import com.api.forum.dtos.LoginCredentialDto
import com.fasterxml.jackson.databind.ObjectMapper
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class JWTLoginFilter(
    private val authManager: AuthenticationManager,
    private val jwtUtil: JWTUtil
) : UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?
    ): Authentication {
        val user  = ObjectMapper().readValue(
            request?.inputStream,
            LoginCredentialDto::class.java
        )

        val token = UsernamePasswordAuthenticationToken(user.email, user.password)

        return authManager.authenticate(token)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        val email = (authResult?.principal as UserDetails).username

        val generatedToken = jwtUtil.generateToken(email)

        response?.addHeader("Authorization", "Bearer $generatedToken")
    }

}
