package com.chatRoom.repositories.room

import com.chatRoom.domainModels.participantAccount.AccountId
import com.chatRoom.domainModels.room.Room
import com.chatRoom.domainModels.room.RoomId

interface IRoomRepository {
    abstract fun findAll(): List<Room>
    abstract fun findByIdOrNull(roomId: RoomId): Room?
    abstract fun findAllByAccountId(accountId: AccountId): List<Room>
    abstract fun findLatestByAccountId(accountId: AccountId): List<Room>
    abstract fun save(room: Room)
    abstract fun deleteByIdOrFailed(roomId: RoomId)
}
