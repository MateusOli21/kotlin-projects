package com.api.forum.models

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "answers")
data class Answer(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, unique = false, length = 120)
    val message: String,

    @ManyToOne
    val author: User,

    @ManyToOne
    val topic: Topic,

    @Column(nullable = false)
    val solved: Boolean = false,

    @Column
    val created_at: LocalDateTime = LocalDateTime.now()
) {
}