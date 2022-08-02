package com.api.forum.utils

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.util.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class JWTUtil {
    private val expiration: Long = 60000

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    fun generateToken(email: String): String? {
        return Jwts.builder()
            .setSubject(email)
            .setExpiration(Date(System.currentTimeMillis() * expiration))
            .signWith(SignatureAlgorithm.HS512, secret.toByteArray())
            .compact()
    }

    fun isValid(token: String?): Boolean {
        return try {
            Jwts.parser()
                .setSigningKey(secret.toByteArray())
                .parseClaimsJws(token)

            true
        }catch(e: IllegalArgumentException) {
            false
        }
    }

    fun getAuthentication(token: String?): Authentication {
        val email = Jwts.parser()
            .setSigningKey(secret.toByteArray())
            .parseClaimsJws(token)

        return UsernamePasswordAuthenticationToken(email, null, null)
    }
}