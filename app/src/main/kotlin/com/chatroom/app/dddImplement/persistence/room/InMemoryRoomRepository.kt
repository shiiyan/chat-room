package com.chatroom.app.dddImplement.persistence.room

import com.chatroom.app.commonConcern.noHyphen
import com.chatroom.app.dddImplement.domain.participantAccount.AccountId
import com.chatroom.app.dddImplement.domain.room.Room
import com.chatroom.app.dddImplement.domain.room.RoomId
import org.springframework.stereotype.Repository
import java.lang.IllegalArgumentException
import java.util.UUID

@Repository
class InMemoryRoomRepository : RoomRepository {
    private val data: MutableList<Room> = mutableListOf()

    override fun generateNextIdentity(): RoomId = RoomId(UUID.randomUUID().toString().noHyphen())

    override fun findAll() = data

    override fun findByIdOrNull(roomId: RoomId): Room? = data.find {
        it.id == roomId
    }

    override fun findAllByAccountId(accountId: AccountId): List<Room> = data.filter {
        AccountId(it.toDto().participantAccountId) == accountId
    }

    override fun findLatestByAccountId(accountId: AccountId): Room? = data.findLast {
        AccountId(it.toDto().participantAccountId) == accountId
    }

    override fun save(room: Room) {
        data.add(room)
    }

    override fun deleteByIdOrFailed(roomId: RoomId) {
        if (findByIdOrNull(roomId) == null) {
            throw IllegalArgumentException("Room Not Found")
        }

        data.removeIf { it.id == roomId }
    }

    public fun clear() {
        data.clear()
    }
}
