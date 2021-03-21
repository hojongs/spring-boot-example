package com.hojong.springbootexample.jpa.user.repository

import com.hojong.springbootexample.jpa.test.TestBase
import com.hojong.springbootexample.jpa.user.entity.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.dao.IncorrectResultSizeDataAccessException
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
@TestBase
class UserRepositoryTest(
    private val userRepository: UserRepository,
) {
    private lateinit var savedTestUser: User

    @BeforeEach
    fun setUp() {
        val user = User.of("test-user")
        savedTestUser = userRepository.save(user)
    }

    @AfterEach
    fun tearDown() {
        userRepository.deleteAll()
    }

    @Nested
    inner class Save {
        @Test
        fun success() {
            // when
            val user = User.of("hojong")
            val saved = userRepository.save(user)

            // then
            val found = userRepository.findByIdOrNull(saved.id)!!
            println(found)

            assertThat(saved.id)
                .isNotEqualTo(0)
        }
    }

    @Nested
    inner class FindByName {
        @Test
        fun `given there is single user matched name, then return the user`() {
            // when
            val found = userRepository.findByName(savedTestUser.name)

            // then
            println(found)
            assertThat(found)
                .isEqualTo(savedTestUser)
        }

        @Test
        fun `given there isn't a user matched name, then return null`() {
            // when
            val found = userRepository.findByName("null")

            // then
            println(found)
            assertThat(found)
                .isNull()
        }

        @Test
        fun `given there are 2 users matched name, then error`() {
            // given
            val user = User.of(savedTestUser.name)
            savedTestUser = userRepository.save(user)

            // when
            assertThrows<IncorrectResultSizeDataAccessException> {
                userRepository.findByName(savedTestUser.name)
            }
        }
    }
}
