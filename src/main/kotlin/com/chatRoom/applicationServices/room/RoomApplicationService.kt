package com.chatRoom.applicationServices.room

import com.chatRoom.domainModels.room.RoomDomainService
import com.chatRoom.domainModels.room.RoomDto
import com.chatRoom.domainModels.room.RoomId
import com.chatRoom.repositories.message.IMessageRepository
import com.chatRoom.repositories.room.IRoomRepository

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
        return RoomDomainService(roomRepository, messageRepository)
            .createRoom(name, level)
    }

    fun lowerRoomLevel(id: String, lowerBy: Int) {
        val room = roomRepository.findByIdOrNull(RoomId(id))!!
        // TODO("Check if current account is the creator of room ")
        room.lowerLevel(lowerBy)
        roomRepository.save(room)
    }

    fun deleteRoom(id: String) {
        RoomDomainService(roomRepository, messageRepository).deleteRoom(id)
    }
}
