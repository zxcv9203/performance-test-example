package org.example.performancetestexample.service

import org.example.performancetestexample.domain.Post
import org.example.performancetestexample.repository.PostRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class PostService(
    private val postRepository: PostRepository
) {
    fun findAll(pageable: Pageable): List<Post> = postRepository.findAll(pageable).toList()
}