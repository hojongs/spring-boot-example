package com.hojong.springbootexample.post.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Transient

@Suppress("LeakingThis")
@Entity
class Post(
    @Id
    @GeneratedValue
    val id: Long,
    val title: String,
    val content: String,
    @Transient
    val myMessage: MyMessage
) {
    private val myMessageString: String = myMessage.msg

    companion object {
        fun createTransient(title: String, content: String, myMessage: MyMessage) =
            Post(0, title, content, myMessage)
    }

    data class MyMessage(
        val msg: String
    )
}
