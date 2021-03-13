package com.hojong.springbootexample.user.repository

import com.hojong.springbootexample.user.entity.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor

@DataJpaTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@ActiveProfiles("test")
class UserRepositoryTest(
    private val userRepository: UserRepository,
) {

    @Test
    fun save() {
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
