package com.chatRoom.domainModels.room.message

import com.chatRoom.domainModels.participantAccount.AccountId
import com.chatRoom.domainModels.room.RoomId
import java.time.LocalDateTime

class Message(
    val id: MessageId,
    private val text: Text,
    private val image: Image,
    val participantAccountId: AccountId,
    val roomId: RoomId,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    companion object {
        fun create(
            text: String,
            imagePaths: List<String>,
            accountId: String,
            roomId: String
        ) = Message(
            id = MessageId(),
            text = Text(text),
            image = Image(imagePaths),
            participantAccountId = AccountId(accountId),
            roomId = RoomId(roomId),
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
    }

    fun updateText(newText: String) {
        text.updateValue(newText)
    }

    fun updateImage() {
    }

    fun toDto(): MessageDto = MessageDto(
        id = id.value,
        text = text.value,
        image = image.pathList,
        participantAccountId = participantAccountId.value,
        roomId = roomId.value,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}
