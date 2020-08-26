package com.chatRoom.domainModelTests.room

import com.chatRoom.domainModels.room.Room
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class RoomDomainModelTest {
    @Test
    fun `test create room successfully`() {
        Room.create(name = "name", level = 10, accountId = "123456")
    }

    @Test
    fun `test create room failed with name too short`() {
        assertThrows<IllegalArgumentException> {
            Room.create(name = "", level = 10, accountId = "123456")
        }
    }

    @Test
    fun `test create room failed with name too long`() {
        assertThrows<IllegalArgumentException> {
            Room.create(name = "namenamenamenamename", level = 10, accountId = "123456")
        }
    }

    @Test
    fun `test create room failed with level too low`() {
        assertThrows<IllegalArgumentException> {
            Room.create(name = "name", level = -10, accountId = "123456")
        }
    }

    @Test
    fun `test create room failed with level too high`() {
        assertThrows<IllegalArgumentException> {
            Room.create(name = "name", level = 1000, accountId = "123456")
        }
    }

    @Test
    fun `test lower room level successfully`() {
        val room = Room.create(name = "name", level = 10, accountId = "123456")
        room.lowerLevel(5)
        assertEquals(5, room.toDto().level)
    }

    @Test
    fun `test lower room level failed with minus level`() {
        val room = Room.create(name = "name", level = 10, accountId = "123456")
        assertThrows<IllegalArgumentException> {
            room.lowerLevel(11)
        }
    }

    @Test
    fun `test lower room level failed with minus lowerBy`() {
        val room = Room.create(name = "name", level = 10, accountId = "123456")
        val exception = assertThrows<Exception> {
            room.lowerLevel(-1)
        }
        assertEquals("Negative Number Forbidden", exception.message)
    }

    @Test
    fun `test is qualified to enter room`() {
        val room = Room.create(name = "name", level = 10, accountId = "123456")
        val highNumberOfMessages = 11
        val lowNumberOfMessages = 9
        assertTrue(room.isQualifiedToEnter(highNumberOfMessages))
        assertFalse(room.isQualifiedToEnter(lowNumberOfMessages))
    }
}
