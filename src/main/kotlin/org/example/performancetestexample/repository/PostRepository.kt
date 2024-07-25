package org.example.performancetestexample.repository

import org.example.performancetestexample.domain.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long>