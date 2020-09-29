package com.chatroom.app.dddImplementTest.portTest.persistenceTest.RoomTest

import com.chatroom.app.dddImplement.domain.participantAccount.AccountId
import com.chatroom.app.dddImplement.domain.room.Level
import com.chatroom.app.dddImplement.domain.room.Name
import com.chatroom.app.dddImplement.domain.room.Room
import com.chatroom.app.dddImplement.domain.room.RoomId
import com.chatroom.app.dddImplement.port.persistence.room.InMemoryRoomRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RoomRepositoryTest(
    @Autowired private val inMemoryRoomRepository: InMemoryRoomRepository
) {
    private val roomToCreate = object {
        val id: String = "972ed988d71a4c649efbd176be436998"
        val name: String = "sample room"
        val level: Int = 10
        val accountId: String = "0fab92fd25d64038b5df4c66e240693e"
    }
    val room1 = Room.create(
        id = RoomId(roomToCreate.id),
        name = Name(roomToCreate.name),
        level = Level(roomToCreate.level),
        accountId = AccountId(roomToCreate.accountId)
    )

    @BeforeAll
    fun setup() {
        inMemoryRoomRepository.clear()
        inMemoryRoomRepository.save(room1)
    }

    @Test
    fun `test find all`() {
        val foundAll = inMemoryRoomRepository.findAll()
        assertEquals(1, foundAll.size)
        assertTrue(foundAll.contains(room1))
    }

    @Test
    fun `test find room by id`() {
        val foundRoom = inMemoryRoomRepository.findByIdOrNull(room1.id)
        assertEquals(room1, foundRoom)
    }
}
