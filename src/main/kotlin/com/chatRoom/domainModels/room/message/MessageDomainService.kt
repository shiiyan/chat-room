package com.chatRoom.domainModels.room.message

import com.chatRoom.repositories.room.message.IMessageRepository

class MessageDomainService(private val messageRepository: IMessageRepository) {
    fun createMessage() {
        // TODO: createMessage
        // TODO: saveMessage
        // TODO: updateRoomWithLatestMessageList
    }

    fun editMessage(messageId: String, newText: String, newImagePaths: List<String>) {
        // TODO: getCurrentAccountId from session yet to imeplemented
        val currentAccountId = "account-id-not-exist"
        val foundMessage = messageRepository.findByIdOrNull(MessageId(messageId))!!

        if (!foundMessage.isCreatorOfMessage(currentAccountId)) {
            throw Exception("No Right To Edit")
        }

        foundMessage.updateText(newText)
        foundMessage.updateImage(newImagePaths)
    }
}
