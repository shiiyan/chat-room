package com.chatRoom.applicationServices.message

import com.chatRoom.domainModels.message.MessageDomainService
import com.chatRoom.domainModels.message.MessageDto
import com.chatRoom.domainModels.room.RoomId
import com.chatRoom.repositories.message.IMessageRepository
import com.chatRoom.repositories.room.IRoomRepository

class MessageApplicationService(
    private val messageRepository: IMessageRepository,
    private val roomRepository: IRoomRepository
) {
    fun getMessageByRoomId(roomId: String): List<MessageDto> {
        // TODO: getCurrentAccountId from session yet to implemented
        val currentAccountId = "account-id-not-exist"
        val foundRoom = roomRepository.findByIdOrNull(RoomId(roomId))!!

        return if (foundRoom.isCreatorOfRoom(currentAccountId))
            MessageDomainService(messageRepository, roomRepository).getMessageByRoomIdForRoomCreator(roomId)
        else
            MessageDomainService(messageRepository, roomRepository).getMessageByRoomIdForParticipant(roomId)
    }

    fun sendMessageToRoom(text: String, imagePaths: List<String>, roomId: String): String {
        // TODO: getCurrentAccountId from session yet to implemented
        val currentAccountId = "account-id-not-exist"
        val foundRoom = roomRepository.findByIdOrNull(RoomId(roomId))!!

        return if (foundRoom.isCreatorOfRoom(currentAccountId))
            MessageDomainService(messageRepository, roomRepository).sendMessageByRoomCreator(text, imagePaths, roomId)
        else
            MessageDomainService(messageRepository, roomRepository).sendMessageByParticipant(text, imagePaths, roomId)
    }

    fun editMessage(messageId: String, newText: String, newImagePaths: List<String>) {
        MessageDomainService(messageRepository, roomRepository)
            .editMessage(messageId, newText, newImagePaths)
    }
}
