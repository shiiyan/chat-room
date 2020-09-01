package com.chatRoom.applicationServiceTests.room

import com.chatRoom.applicationServices.room.RoomApplicationService
import com.chatRoom.repositories.room.IRoomRepository
import com.chatRoom.repositories.room.InMemoryRoomRepository
import com.chatRoom.repositories.room.message.IMessageRepository
import com.chatRoom.repositories.room.message.InMemoryMessageRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.Exception

class RoomApplicationServiceTest {
    private lateinit var roomRepository: IRoomRepository
    private lateinit var messageRepository: IMessageRepository
    private lateinit var roomApplicationService: RoomApplicationService
    private val roomToCreate = object {
        val name: String = "name"
        val level: Int = 10
    }

    @BeforeEach
    fun init() {
        roomRepository = InMemoryRoomRepository()
        messageRepository = InMemoryMessageRepository()
        roomApplicationService = RoomApplicationService(roomRepository, messageRepository)
    }

    @Test
    fun `test get room by id`() {
        val id = roomApplicationService.createRoom(
            name = roomToCreate.name,
            level = roomToCreate.level
        )
        val roomDto = roomApplicationService.getRoomById(id)

        assertEquals(id, roomDto.id)
        assertEquals(roomToCreate.name, roomDto.name)
        assertEquals(roomToCreate.level, roomDto.level)
    }

    @Test
    fun `test create room successfully`() {
        roomApplicationService.createRoom(
            name = roomToCreate.name,
            level = roomToCreate.level
        )
    }

    @Test
    fun `test create room failed by plural rooms`() {
        roomApplicationService.createRoom(
            name = roomToCreate.name,
            level = roomToCreate.level
        )
        val exception = assertThrows<Exception> {
            roomApplicationService.createRoom(
                name = roomToCreate.name,
                level = roomToCreate.level
            )
        }

        assertEquals("No Right To Create Room", exception.message)
    }

    @Test
    fun `test lower room level`() {
        val id = roomApplicationService.createRoom(
            name = roomToCreate.name,
            level = roomToCreate.level
        )
        roomApplicationService.lowerRoomLevel(id, 5)
        val roomDto = roomApplicationService.getRoomById(id)

        assertEquals(5, roomDto.level)
    }
}
