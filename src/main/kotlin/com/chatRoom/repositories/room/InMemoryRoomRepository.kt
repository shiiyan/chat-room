package com.chatRoom.repositories.room

import com.chatRoom.domainModels.participantAccount.AccountId
import com.chatRoom.domainModels.room.Room
import com.chatRoom.domainModels.room.RoomId

class InMemoryRoomRepository : IRoomRepository {
    private val data: MutableMap<RoomId, Room> = mutableMapOf()

    override fun findAll() = data.values.map { it }

    override fun findByIdOrNull(roomId: RoomId): Room? = data[roomId]

    override fun findAllByAccountId(accountId: AccountId): List<Room> {
        val filteredData = data.filter {
            (_, value) ->
            value.toDto().participantAccountId == accountId.value
        }

        return filteredData.values.map { it }
    }

    override fun findLatestByAccountId(accountId: AccountId): List<Room> {
        val filteredData = data.filter {
            (_, value) ->
            value.toDto().participantAccountId == accountId.value
        }

        return filteredData.values.sortedByDescending { it.toDto().createdAt }
            .take(1)
    }

    override fun save(room: Room) {
        data[room.id] = room
    }

    override fun deleteByIdOrFailed(roomId: RoomId) {
        if (data.remove(roomId) == null) {
            throw Exception("Room not found")
        }
    }
}
