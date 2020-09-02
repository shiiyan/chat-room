package com.chatRoom.applicationServices.message

import com.chatRoom.domainModels.message.MessageDomainService
import com.chatRoom.repositories.message.IMessageRepository
import com.chatRoom.repositories.room.IRoomRepository

class MessageApplicationService(
    private val messageRepository: IMessageRepository,
    private val roomRepository: IRoomRepository
) {
    fun getMessageByRoomId() {
        TODO("Not yet implemented")
    }

    fun getMessageByAccountId() {
        TODO("Not yet implemented")
    }

    fun sendMessage(text: String, imagePaths: List<String>, roomId: String): String {

        return MessageDomainService(messageRepository, roomRepository)
            .sendMessage(text, imagePaths, roomId)
    }

    fun editMessage(messageId: String, newText: String, newImagePaths: List<String>) {
        MessageDomainService(messageRepository, roomRepository)
            .editMessage(messageId, newText, newImagePaths)
    }
}
