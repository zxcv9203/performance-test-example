package org.example.performancetestexample.service

import org.example.performancetestexample.domain.Post
import org.example.performancetestexample.repository.PostRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class PostService(
    private val postRepository: PostRepository
) {
    @Cacheable("posts")
    fun findAll(pageable: Pageable): Page<Post> = postRepository.findAll(pageable)
}