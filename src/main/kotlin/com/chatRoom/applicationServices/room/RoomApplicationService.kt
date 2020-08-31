package com.chatRoom.applicationServices.room

import com.chatRoom.domainModels.room.RoomDomainService
import com.chatRoom.domainModels.room.RoomDto
import com.chatRoom.domainModels.room.RoomId
import com.chatRoom.repositories.room.IRoomRepository

class RoomApplicationService(private val roomRepository: IRoomRepository) {
    fun getRoomById(id: String): RoomDto {
        return roomRepository.findByIdOrNull(RoomId(id))
            ?.toDto()
            ?: throw Exception("Room Not Found")
    }

    fun createRoom(name: String, level: Int, accountId: String): String {
        val roomId = RoomDomainService(roomRepository).createRoom(name, level, accountId)

        return roomId
    }

    fun lowerRoomLevel(id: String, lowerBy: Int) {
        val room = roomRepository.findByIdOrNull(RoomId(id))!!
        room.lowerLevel(lowerBy)
        roomRepository.save(room)
    }
}
