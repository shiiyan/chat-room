package com.chatRoom.domainModels.room

import java.time.LocalDateTime

data class RoomDto(
    val id: String,
    val name: String,
    val level: Int,
    val participantAccountId: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
