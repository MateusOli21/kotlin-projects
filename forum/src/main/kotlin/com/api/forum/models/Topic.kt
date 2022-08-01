package com.api.forum.models

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "topics")
data class Topic(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, unique = false, length = 120)
    val title: String = "",

    @Column(nullable = false, unique = false, length = 250)
    val message: String = "",

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    val course: Course,

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    val author: User,

    @OneToMany(mappedBy = "topic")
    val answers: List<Answer> = ArrayList(),

    @Column
    val created_at: LocalDateTime = LocalDateTime.now()
)
