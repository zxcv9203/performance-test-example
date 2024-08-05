package org.example.performancetestexample.service

import org.example.performancetestexample.controller.request.PostUpdateRequest
import org.example.performancetestexample.domain.Post
import org.example.performancetestexample.repository.PostRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PostService(
    private val postRepository: PostRepository
) {
    @Transactional(readOnly = true)
    @Cacheable("posts")
    fun findAll(pageable: Pageable): Page<Post> = postRepository.findAll(pageable)

    fun update(id: Long, request: PostUpdateRequest) {
        val post = postRepository.findByIdOrNull(id)
            ?: throw IllegalArgumentException("게시글이 존재하지 않습니다.")

        post.title = request.title
        post.content = request.content

        postRepository.save(post)
    }
}