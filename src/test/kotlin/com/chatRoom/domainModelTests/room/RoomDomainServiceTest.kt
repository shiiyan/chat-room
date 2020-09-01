package com.chatRoom.domainModelTests.room

import com.chatRoom.domainModels.room.RoomDomainService
import com.chatRoom.repositories.room.IRoomRepository
import com.chatRoom.repositories.room.InMemoryRoomRepository
import com.chatRoom.repositories.room.message.IMessageRepository
import com.chatRoom.repositories.room.message.InMemoryMessageRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.Exception

class RoomDomainServiceTest {
    private lateinit var roomRepository: IRoomRepository
    private lateinit var messageRepository: IMessageRepository
    private lateinit var roomDomainService: RoomDomainService
    private val roomToCreate = object {
        val name: String = "name"
        val level: Int = 10
    }

    @BeforeEach
    fun init() {
        roomRepository = InMemoryRoomRepository()
        messageRepository = InMemoryMessageRepository()
        roomDomainService = RoomDomainService(roomRepository, messageRepository)
    }

    @Test
    fun `test create room successfully`() {
        roomDomainService.createRoom(
            name = roomToCreate.name,
            level = roomToCreate.level
        )
    }

    @Test
    fun `test create room failed by plural rooms`() {
        roomDomainService.createRoom(
            name = roomToCreate.name,
            level = roomToCreate.level
        )
        val exception = assertThrows<Exception> {
            roomDomainService.createRoom(
                name = roomToCreate.name,
                level = roomToCreate.level
            )
        }

        assertEquals("No Right To Create Room", exception.message)
    }
}
