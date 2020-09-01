package com.chatRoom.domainModels.room

import com.chatRoom.domainModels.participantAccount.AccountId
import com.chatRoom.repositories.room.IRoomRepository
import com.chatRoom.repositories.room.message.IMessageRepository

class RoomDomainService(
    private val roomRepository: IRoomRepository,
    private val messageRepository: IMessageRepository
) {
    fun createRoom(name: String, level: Int): String {
        // TODO: getCurrentAccountId from session yet to imeplemented
        val currentAccountId = "account-id-not-exist"
        val latestCreatedRooms = roomRepository.findLatestByAccountId(AccountId(currentAccountId))
        val latestSentMessages = messageRepository.findLatestByAccountId(AccountId(currentAccountId))
        if (latestSentMessages.isEmpty() || latestCreatedRooms.isNotEmpty()) {
            throw Exception("No Right To Create Room")
        }

        val room = Room.create(name = name, level = level, accountId = currentAccountId)
        roomRepository.save(room)

        return room.id.value
    }
}
