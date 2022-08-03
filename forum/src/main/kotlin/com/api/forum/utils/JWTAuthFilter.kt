package com.api.forum.utils

import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JWTAuthFilter(
    private val jwtUtil: JWTUtil
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authToken = request.getHeader("Authorization")

        val jwtToken = getJwtToken(authToken)

        if (jwtUtil.isValid(jwtToken)) {
            val authentication = jwtUtil.getAuthentication(jwtToken)
            SecurityContextHolder.getContext().authentication = authentication
        }

        filterChain.doFilter(request, response)
    }

    private fun getJwtToken(token: String?): String? {
        return token?.let { jwt ->
            jwt.startsWith("Bearer ")
            jwt.substring(7, jwt.length)
        }
    }
}
