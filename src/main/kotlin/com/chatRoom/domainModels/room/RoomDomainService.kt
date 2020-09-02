package com.chatRoom.domainModels.room

import com.chatRoom.domainModels.message.MessageId
import com.chatRoom.domainModels.participantAccount.AccountId
import com.chatRoom.repositories.message.IMessageRepository
import com.chatRoom.repositories.room.IRoomRepository

class RoomDomainService(
    private val roomRepository: IRoomRepository,
    private val messageRepository: IMessageRepository
) {
    fun createRoom(name: String, level: Int): String {
        // TODO: getCurrentAccountId from session yet to implemented
        val currentAccountId = "account-id-not-exist"
        val latestCreatedRooms = roomRepository.findLatestByAccountId(AccountId(currentAccountId))
        val latestSentMessages = messageRepository.findLatestByAccountId(AccountId(currentAccountId))
        if (latestSentMessages.isEmpty() && latestCreatedRooms.isNotEmpty()) {
            throw Exception("No Right To Create Room")
        }

        val room = Room.create(name = name, level = level, accountId = currentAccountId)
        roomRepository.save(room)

        return room.id.value
    }

    fun deleteRoom(roomId: String) {
        if (!isRoomDeletableByParticipant(roomId) && !isRoomDeletableByCreator(roomId)) {
            throw Exception("Room Not Deletable")
        }

        roomRepository.deleteByIdOrFailed(RoomId(roomId))
    }

    private fun isRoomDeletableByParticipant(roomId: String): Boolean {
        val foundRoom = roomRepository.findByIdOrNull(RoomId(roomId))!!
        val latestMessageId = foundRoom.toDto().latestMessageIdList.firstOrNull()

        if (foundRoom.getSecondsSinceCreated() < 60 * 60) {
            return false
        }

        if (latestMessageId != null) {
            val foundMessage = messageRepository.findByIdOrNull(MessageId(latestMessageId))!!
            if (foundMessage.getSecondsSinceCreated() < 60 * 60) {
                return false
            }
        }

        return true
    }

    private fun isRoomDeletableByCreator(roomId: String): Boolean {
        val foundRoom = roomRepository.findByIdOrNull(RoomId(roomId))!!
        // TODO: getCurrentAccountId from session yet to implemented
        val currentAccountId = "account-id-not-exist"
        if (!foundRoom.isCreatorOfRoom(currentAccountId)) {
            return false
        }

//        if (false) {
//            return false
//        }

        return true
    }
}
