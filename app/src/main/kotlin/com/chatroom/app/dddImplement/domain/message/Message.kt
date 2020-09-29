package com.chatroom.ddd.domainModels.message

import com.chatroom.app.dddImplement.domain.message.ImagePathList
import com.chatroom.app.dddImplement.domain.participantAccount.AccountId
import com.chatroom.app.dddImplement.domain.room.RoomId

class Message(
    val id: MessageId,
    private val text: Text,
    private val imagePathList: ImagePathList,
    private val participantAccountId: AccountId,
    private val roomId: RoomId
) {
    companion object {
        fun create(
            messageId: MessageId,
            text: Text,
            imagePathList: ImagePathList,
            accountId: AccountId,
            roomId: RoomId
        ) = Message(
            id = messageId,
            text = text,
            imagePathList = imagePathList,
            participantAccountId = accountId,
            roomId = roomId
        )
    }

//    fun updateText(newText: String) {
//        if (getSecondsSinceCreated() > 15 * 60) {
//            throw Exception("Message Not Editable")
//        }
//
//        text.updateValue(newText)
//        updatedAt.changeDateTime(LocalDateTime.now())
//    }
//
//    fun updateImage(newImagePathList: List<String>) {
//        if (getSecondsSinceCreated() > 15 * 60) {
//            throw Exception("Message Not Editable")
//        }
//
//        image.updatePathList(newImagePathList)
//        updatedAt.changeDateTime(LocalDateTime.now())
//    }
//
//    fun getSecondsSinceCreated(): Long {
//        return Duration.between(createdAt.dateTime, LocalDateTime.now()).seconds
//    }
//
//    fun isCreatorOfMessage(accountId: String): Boolean = accountId == participantAccountId.value

    fun toDto(): MessageDto = MessageDto(
        id = id.value,
        text = text.value,
        image = imagePathList.value,
        participantAccountId = participantAccountId.value,
        roomId = roomId.value
    )
}
