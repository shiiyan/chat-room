package com.chatRoom.domainModels.message

import java.time.LocalDateTime

data class MessageDto(
    val id: String,
    val text: String,
    val image: List<String>,
    val participantAccountId: String,
    val roomId: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
