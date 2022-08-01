package com.api.forum.models

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, length = 120)
    val name: String = "",

    @Column(nullable = false, length = 40)
    val email: String = "",

    @Column(nullable = false, length = 13)
    val phone: String = ""
)
