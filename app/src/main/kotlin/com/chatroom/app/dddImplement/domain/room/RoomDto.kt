package com.chatroom.app.dddImplement.domain.room

data class RoomDto(
    val id: String,
    val name: String,
    val level: Int,
    val participantAccountId: String
)
