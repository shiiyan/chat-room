package com.chatRoom.domainModels.room.message

import com.chatRoom.domainModels.participantAccount.AccountId
import com.chatRoom.domainModels.room.RoomId
import java.time.Duration
import java.time.LocalDateTime

class Message(
    val id: MessageId,
    private val text: Text,
    private val image: Image,
    val participantAccountId: AccountId,
    val roomId: RoomId,
    val createdAt: CreatedAt,
    val updatedAt: UpdatedAt
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
            createdAt = CreatedAt(LocalDateTime.now()),
            updatedAt = UpdatedAt(LocalDateTime.now())
        )
    }

    fun updateText(newText: String) {
        if (getSecondsSinceCreated() > 15 * 60) {
            throw Exception("Message Not Editable")
        }

        text.updateValue(newText)
        updatedAt.changeDateTime(LocalDateTime.now())
    }

    fun updateImage(newImagePathList: List<String>) {
        if (getSecondsSinceCreated() > 15 * 60) {
            throw Exception("Message Not Editable")
        }

        image.updatePathList(newImagePathList)
        updatedAt.changeDateTime(LocalDateTime.now())
    }

    private fun getSecondsSinceCreated(): Long {
        return Duration.between(LocalDateTime.now(), createdAt.dateTime).seconds
    }

    fun isCreatorOfMessage(accountId: String): Boolean = accountId == participantAccountId.value

    fun toDto(): MessageDto = MessageDto(
        id = id.value,
        text = text.value,
        image = image.pathList,
        participantAccountId = participantAccountId.value,
        roomId = roomId.value,
        createdAt = createdAt.dateTime,
        updatedAt = updatedAt.dateTime
    )
}
