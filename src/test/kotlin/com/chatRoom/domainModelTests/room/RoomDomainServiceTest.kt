package com.chatRoom.domainModelTests.room

import com.chatRoom.domainModels.participantAccount.ParticipantAccount
import com.chatRoom.domainModels.room.RoomDomainService
import com.chatRoom.repositories.room.IRoomRepository
import com.chatRoom.repositories.room.InMemoryRoomRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.Exception

class RoomDomainServiceTest {
    private lateinit var roomRepository: IRoomRepository
    private lateinit var roomDomainService: RoomDomainService
    private val participantAccount = ParticipantAccount.create(name = "name", iconPath = "iconPath")
    private val roomToCreate = object {
        val name: String = "name"
        val level: Int = 10
    }

    @BeforeEach
    fun init() {
        roomRepository = InMemoryRoomRepository()
        roomDomainService = RoomDomainService(roomRepository)
    }

    @Test
    fun `test create room successfully`() {
        roomDomainService.createRoom(
            name = roomToCreate.name,
            level = roomToCreate.level,
            accountId = participantAccount.id.value
        )
    }

    @Test
    fun `test create room failed by plural rooms`() {
        roomDomainService.createRoom(
            name = roomToCreate.name,
            level = roomToCreate.level,
            accountId = participantAccount.id.value
        )
        val exception = assertThrows<Exception> {
            roomDomainService.createRoom(
                name = roomToCreate.name,
                level = roomToCreate.level,
                accountId = participantAccount.id.value
            )
        }

        assertEquals("Plural Rooms Forbidden", exception.message)
    }
}
