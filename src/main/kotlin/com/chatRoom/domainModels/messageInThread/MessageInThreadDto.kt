package com.chatRoom.domainModels.messageInThread

import java.time.LocalDateTime

data class MessageInThreadDto(
        val id: String,
        val text: String,
        val image: List<String>,
        val participantAccountId: String,
        val threadId: String,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime
)
