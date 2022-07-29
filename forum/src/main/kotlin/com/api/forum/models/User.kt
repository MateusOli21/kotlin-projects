package com.api.forum.models

import javax.persistence.*

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
) {
}