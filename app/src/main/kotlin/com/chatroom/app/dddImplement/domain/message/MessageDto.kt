package com.chatroom.ddd.domainModels.message

data class MessageDto(
    val id: String,
    val text: String,
    val image: List<String>,
    val participantAccountId: String,
    val roomId: String
)
