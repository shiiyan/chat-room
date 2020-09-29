package com.chatroom.app.dddImplement.persistence.room

import com.chatroom.app.dddImplement.domain.participantAccount.AccountId
import com.chatroom.app.dddImplement.domain.room.Room
import com.chatroom.app.dddImplement.domain.room.RoomId

interface RoomRepository {
    abstract fun generateNextIdentity(): RoomId
    abstract fun findAll(): List<Room>
    abstract fun findByIdOrNull(roomId: RoomId): Room?
    abstract fun findAllByAccountId(accountId: AccountId): List<Room>
    abstract fun findLatestByAccountId(accountId: AccountId): Room?
    abstract fun save(room: Room)
    abstract fun deleteByIdOrFailed(roomId: RoomId)
}
