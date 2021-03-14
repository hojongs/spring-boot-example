package com.hojong.springbootexample.post.service

import com.hojong.springbootexample.App
import com.hojong.springbootexample.post.dto.CreatePostRequet
import com.hojong.springbootexample.post.repository.PostRepository
import com.hojong.springbootexample.test.TestBase
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import reactor.kotlin.test.test

@SpringBootTest(
    classes = [App::class],
)
@TestBase
class PostServiceTest(
    private val postService: PostService,
    private val postRepository: PostRepository,
) {
    @AfterEach
    fun tearDown() {
        postRepository.deleteAll()
    }

    @Nested
    inner class CreatePost {
        @Test
        fun success() {
            val req = CreatePostRequet(
                1,
                "my post",
                "example content"
            )
            postService
                .createPost(req)
                .test()
                .assertNext { actual ->
                    val expected = req.toPost()
                    assertThat(actual.id)
                        .isNotEqualTo(0)
                    assertThat(actual.title)
                        .isEqualTo(expected.title)
                    assertThat(actual.content)
                        .isEqualTo(expected.content)
                }
                .verifyComplete()
        }
    }
}
