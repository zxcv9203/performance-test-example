package org.example.performancetestexample.domain

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Post(

    @Column(name = "title", nullable = false, length = 100)
    var title: String,

    @Lob
    @Column(name = "content", nullable = false)
    var content: String,

    @Column(name = "author", nullable = false, length = 50)
    val author: String,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime,

    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
)