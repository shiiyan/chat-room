package com.chatRoom.domainModels.thread.message

import java.time.LocalDateTime

data class MessageDto(
    val id: String,
    val text: String,
    val image: List<String>,
    val participantAccountId: String,
    val threadId: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
