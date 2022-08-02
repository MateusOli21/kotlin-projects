package com.api.forum.configs

import com.api.forum.utils.JWTAuthFilter
import com.api.forum.utils.JWTLoginFilter
import com.api.forum.utils.JWTUtil
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val userDetailsService: UserDetailsService,
    private val jwtUtil: JWTUtil
): WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity?) {
        http?.
        csrf()?.disable()?.
        authorizeRequests()?.
        antMatchers(HttpMethod.POST,"/login", "/users")?.permitAll()?.
        anyRequest()?.authenticated()?.
        and()?.
        addFilterBefore(
            JWTLoginFilter(
                authManager = authenticationManager(),
                jwtUtil = jwtUtil
            ),
            UsernamePasswordAuthenticationFilter().javaClass
        )?.
        addFilterBefore(
            JWTAuthFilter(jwtUtil = jwtUtil),
            UsernamePasswordAuthenticationFilter().javaClass
        )?.
        sessionManagement()?.
        sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()

    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.
        userDetailsService(userDetailsService)?.
        passwordEncoder(passwordEncoder())
    }
}



