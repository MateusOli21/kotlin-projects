package com.api.forum.models

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "courses")
data class Course(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, unique = false, length = 120)
    val name: String = "",

    @Column(nullable = false, unique = false, length = 120)
    val category: String = ""
) {

}
