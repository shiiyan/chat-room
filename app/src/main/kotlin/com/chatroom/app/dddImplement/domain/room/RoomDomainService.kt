package com.chatroom.app.dddImplement.domain.room

import com.chatroom.app.dddImplement.domain.participantAccount.AccountId
import com.chatroom.app.dddImplement.port.persistence.message.MessageRepository
import com.chatroom.app.dddImplement.port.persistence.room.RoomRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RoomDomainService(
    @Autowired val roomRepository: RoomRepository,
    @Autowired val messageRepository: MessageRepository
) {
    fun hasRightToCreateRoom(accountId: AccountId): Boolean {
        val lastCreatedRoom = roomRepository.findLatestByAccountId(accountId)
        val lastSentMessage = messageRepository.findLatestByAccountId(accountId)

        if (lastSentMessage == null && lastCreatedRoom !== null) {
            return false
        }

        return true
    }

    //    fun deleteRoom(roomId: String) {
    //        if (!isRoomDeletableByParticipant(roomId) && !isRoomDeletableByCreator(roomId)) {
    //            throw Exception("Room Not Deletable")
    //        }
    //
    //        roomRepository.deleteByIdOrFailed(RoomId(roomId))
    //    }

    //    private fun isRoomDeletableByParticipant(roomId: String): Boolean {
    //        val foundRoom = roomRepository.findByIdOrNull(RoomId(roomId))!!
    //        val latestMessageId = foundRoom.toDto().latestMessageIdList.firstOrNull()
    //
    //        if (foundRoom.getSecondsSinceCreated() < 60 * 60) {
    //            return false
    //        }
    //
    //        if (latestMessageId != null) {
    //            val foundMessage = messageRepository.findByIdOrNull(MessageId(latestMessageId))!!
    //            if (foundMessage.getSecondsSinceCreated() < 60 * 60) {
    //                return false
    //            }
    //        }
    //
    //        return true
    //    }
    //
    //    private fun isRoomDeletableByCreator(roomId: String): Boolean {
    //        val foundRoom = roomRepository.findByIdOrNull(RoomId(roomId))!!
    //        // TODO: getCurrentAccountId from session yet to implemented
    //        val currentAccountId = "account-id-not-exist"
    //        if (
    //            !foundRoom.isCreatorOfRoom(currentAccountId) ||
    //            foundRoom.toDto().latestMessageIdList.isNotEmpty()
    //        ) {
    //            return false
    //        }
    //
    //        return true
    //    }
}
