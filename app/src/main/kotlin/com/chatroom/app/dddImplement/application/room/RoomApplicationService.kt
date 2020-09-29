package com.chatroom.app.dddImplement.application.room

import com.chatroom.app.dddImplement.domain.participantAccount.AccountId
import com.chatroom.app.dddImplement.domain.room.Level
import com.chatroom.app.dddImplement.domain.room.Name
import com.chatroom.app.dddImplement.domain.room.Room
import com.chatroom.app.dddImplement.domain.room.RoomDomainService
import com.chatroom.app.dddImplement.port.persistence.message.MessageRepository
import com.chatroom.app.dddImplement.port.persistence.room.RoomRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RoomApplicationService(
    @Autowired private val roomRepository: RoomRepository,
    @Autowired private val messageRepository: MessageRepository,
    @Autowired private val roomDomainService: RoomDomainService
) {
//    fun getRoomById(id: String): RoomDto {
//        return roomRepository.findByIdOrNull(RoomId(id))
//            ?.toDto()
//            ?: throw Exception("Room Not Found")
//    }

    fun createRoom(name: String, level: Int, currentAccountId: String): String {
        val roomId = roomRepository.generateNextIdentity()

        if (
            !roomDomainService.hasRightToCreateRoom(AccountId(currentAccountId))
        ) {
            throw IllegalAccessException("No Right To Create Room")
        }

        val room = Room.create(
            id = roomId,
            name = Name(name),
            level = Level(level),
            accountId = AccountId(currentAccountId)
        )
        roomRepository.save(room)

        return room.id.value
    }

//    fun lowerRoomLevel(id: String, lowerBy: Int) {
//        val room = roomRepository.findByIdOrNull(RoomId(id))!!
//        // TODO("Check if current account is the creator of room ")
//        room.lowerLevel(lowerBy)
//        roomRepository.save(room)
//    }
//
//    fun deleteRoom(id: String) {
//        RoomDomainService(roomRepository, messageRepository).deleteRoom(id)
//    }
}
