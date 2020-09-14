package com.chatRoom.domainModels.thread

import java.time.LocalDateTime

data class ThreadDto(
    val id: String,
    val latestMessageList: List<String>,
    val participantAccountId: String,
    val messageId: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
