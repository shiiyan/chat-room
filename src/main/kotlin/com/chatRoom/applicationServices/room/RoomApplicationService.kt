package com.chatRoom.applicationServices.room

import com.chatRoom.domainModels.room.RoomDomainService
import com.chatRoom.domainModels.room.RoomDto
import com.chatRoom.domainModels.room.RoomId
import com.chatRoom.repositories.room.IRoomRepository
import com.chatRoom.repositories.room.message.IMessageRepository

class RoomApplicationService(
    private val roomRepository: IRoomRepository,
    private val messageRepository: IMessageRepository
) {
    fun getRoomById(id: String): RoomDto {
        return roomRepository.findByIdOrNull(RoomId(id))
            ?.toDto()
            ?: throw Exception("Room Not Found")
    }

    fun createRoom(name: String, level: Int): String {
        val roomId = RoomDomainService(roomRepository, messageRepository).createRoom(name, level)

        return roomId
    }

    fun lowerRoomLevel(id: String, lowerBy: Int) {
        val room = roomRepository.findByIdOrNull(RoomId(id))!!
        room.lowerLevel(lowerBy)
        roomRepository.save(room)
    }
}
