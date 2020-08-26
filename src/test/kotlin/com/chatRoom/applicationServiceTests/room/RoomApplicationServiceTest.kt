package com.chatRoom.applicationServiceTests.room

import com.chatRoom.applicationServices.room.RoomApplicationService
import com.chatRoom.domainModels.participantAccount.ParticipantAccount
import com.chatRoom.repositories.room.IRoomRepository
import com.chatRoom.repositories.room.InMemoryRoomRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.Exception

class RoomApplicationServiceTest {
    private lateinit var roomRepository: IRoomRepository
    private lateinit var roomApplicationService: RoomApplicationService
    private val participantAccount = ParticipantAccount.create(name = "name", iconPath = "iconPath")
    private val roomToCreate = object {
        val name: String = "name"
        val level: Int = 10
    }

    @BeforeEach
    fun init() {
        roomRepository = InMemoryRoomRepository()
        roomApplicationService = RoomApplicationService(roomRepository)
    }

    @Test
    fun `test get room by id`() {
        val id = roomApplicationService.createRoom(
            name = roomToCreate.name,
            level = roomToCreate.level,
            accountId = participantAccount.id.value
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
            level = roomToCreate.level,
            accountId = participantAccount.id.value
        )
    }

    @Test
    fun `test create room failed by plural rooms`() {
        roomApplicationService.createRoom(
            name = roomToCreate.name,
            level = roomToCreate.level,
            accountId = participantAccount.id.value
        )
        val exception = assertThrows<Exception> {
            roomApplicationService.createRoom(
                name = roomToCreate.name,
                level = roomToCreate.level,
                accountId = participantAccount.id.value
            )
        }

        assertEquals("Plural Rooms Forbidden", exception.message)
    }

    @Test
    fun `test lower room level`() {
        val id = roomApplicationService.createRoom(
            name = roomToCreate.name,
            level = roomToCreate.level,
            accountId = participantAccount.id.value
        )
        roomApplicationService.lowerRoomLevel(id, 5)
        val roomDto = roomApplicationService.getRoomById(id)

        assertEquals(5, roomDto.level)
    }
}
