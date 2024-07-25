package org.example.performancetestexample.controller

import org.example.performancetestexample.service.PostService
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PostController(
    private val postService: PostService
) {

    @GetMapping("/posts")
    fun findAll(pageable: Pageable) = postService.findAll(pageable)
}