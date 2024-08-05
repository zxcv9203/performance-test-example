package org.example.performancetestexample.controller

import org.example.performancetestexample.controller.request.PostUpdateRequest
import org.example.performancetestexample.service.PostService
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

@RestController
class PostController(
    private val postService: PostService
) {

    @GetMapping("/posts")
    fun findAll(pageable: Pageable) = postService.findAll(pageable)

    @PatchMapping("/posts/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody request: PostUpdateRequest
    ) = postService.update(id, request)

}