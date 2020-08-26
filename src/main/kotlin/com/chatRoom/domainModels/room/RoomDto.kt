package com.chatRoom.domainModels.room

data class RoomDto(
    val id: String,
    val name: String,
    val level: Int,
    val participantAccountId: String
)
