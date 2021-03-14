package com.hojong.springbootexample.post.repository

import com.hojong.springbootexample.post.entity.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long>
