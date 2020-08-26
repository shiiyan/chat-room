package com.chatRoom.repositories.room

import com.chatRoom.domainModels.participantAccount.AccountId
import com.chatRoom.domainModels.room.Room
import com.chatRoom.domainModels.room.RoomId

class RoomRepository : IRoomRepository {
    override fun findAll(): List<Room> {
        TODO("Not yet implemented")
    }

    override fun findByIdOrNull(roomId: RoomId): Room? {
        TODO("Not yet implemented")
    }

    override fun findByAccountId(accountId: AccountId): List<Room> {
        TODO("Not yet implemented")
    }

    override fun save(room: Room) {
        TODO("Not yet implemented")
    }

    override fun deleteByIdOrFailed(roomId: RoomId) {
        TODO("Not yet implemented")
    }
}
