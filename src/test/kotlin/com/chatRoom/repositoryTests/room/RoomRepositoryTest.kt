package com.chatRoom.repositoryTests.room

import com.chatRoom.domainModels.participantAccount.AccountId
import com.chatRoom.domainModels.room.Room
import com.chatRoom.repositories.room.IRoomRepository
import com.chatRoom.repositories.room.InMemoryRoomRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.Exception

class RoomRepositoryTest {
    private lateinit var roomRepository: IRoomRepository
    private val room1: Room = Room.create(name = "name1", level = 10, accountId = "123")
    private val room2: Room = Room.create(name = "name2", level = 20, accountId = "456")

    @BeforeEach
    fun init() {
        roomRepository = InMemoryRoomRepository()
        roomRepository.save(room1)
    }

    @Test
    fun `test find all`() {
        val foundAll = roomRepository.findAll()
        assertEquals(1, foundAll.size)
        assertTrue(foundAll.contains(room1))
    }

    @Test
    fun `test find room by Id`() {
        val foundRoom = roomRepository.findByIdOrNull(room1.id)
        assertEquals(room1, foundRoom)
    }

    @Test
    fun `test find room by account id`() {
        val foundRooms = roomRepository.findByAccountId(AccountId("123"))
        assertEquals(1, foundRooms.size)
        assertTrue(foundRooms.contains(room1))
    }

    @Test
    fun `test save room`() {
        roomRepository.save(room2)
        val foundAll = roomRepository.findAll()
        assertEquals(2, foundAll.size)
        assertTrue(foundAll.contains(room1))
        assertTrue(foundAll.contains(room2))
    }

    @Test
    fun `test delete room by id successfully`() {
        roomRepository.deleteByIdOrFailed(room1.id)
        val foundRoom = roomRepository.findByIdOrNull(room1.id)
        assertEquals(null, foundRoom)
    }

    @Test
    fun `test delete room by id failed with wrong id`() {
        val exception = assertThrows<Exception> {
            roomRepository.deleteByIdOrFailed(room2.id)
        }
        assertEquals("Room not found", exception.message)
    }
}
