package com.hojong.springbootexample.post.service

import com.hojong.springbootexample.post.dto.CreatePostRequet
import com.hojong.springbootexample.post.entity.Post
import com.hojong.springbootexample.post.repository.PostRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Service
class PostService(
    private val postRepository: PostRepository,
) {
    fun createPost(createPostRequet: CreatePostRequet): Mono<Post> =
        createPostRequet
            .toPost()
            .toMono()
            .map { post -> postRepository.save(post) }
}
