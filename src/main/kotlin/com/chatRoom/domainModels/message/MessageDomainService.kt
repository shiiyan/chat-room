package com.chatRoom.domainModels.message

import com.chatRoom.domainModels.room.RoomId
import com.chatRoom.intraAggregateDataClasses.IntraAggregateMessageId
import com.chatRoom.repositories.message.IMessageRepository
import com.chatRoom.repositories.room.IRoomRepository

class MessageDomainService(
    private val messageRepository: IMessageRepository,
    private val roomRepository: IRoomRepository
) {
    fun sendMessage(text: String, imagePaths: List<String>, roomId: String): String {
        // TODO: getCurrentAccountId from session yet to implemented
        val currentAccountId = "account-id-not-exist"
        val foundRoom = roomRepository.findByIdOrNull(RoomId(roomId))!!

        val newMessage = Message.create(text, imagePaths, currentAccountId, roomId)
        messageRepository.save(newMessage)

        foundRoom.updateLatestMessageList(
            listOf(IntraAggregateMessageId(newMessage.toDto().id))
        )
        roomRepository.save(foundRoom)

        return newMessage.toDto().id
    }

    fun editMessage(messageId: String, newText: String, newImagePaths: List<String>) {
        // TODO: getCurrentAccountId from session yet to implemented
        val currentAccountId = "account-id-not-exist"
        val foundMessage = messageRepository.findByIdOrNull(MessageId(messageId))!!

        if (!foundMessage.isCreatorOfMessage(currentAccountId)) {
            throw Exception("No Right To Edit")
        }

        foundMessage.updateText(newText)
        foundMessage.updateImage(newImagePaths)
    }
}
