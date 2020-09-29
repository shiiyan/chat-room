package com.chatroom.app.dddImplementTest.applicationTest.roomTest

import com.chatroom.app.dddImplement.application.room.RoomApplicationService
import com.chatroom.app.dddImplement.domain.room.RoomId
import com.chatroom.app.dddImplement.port.persistence.room.InMemoryRoomRepository
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RoomApplicationServiceTest(
    @Autowired private val roomApplicationService: RoomApplicationService,
    @Autowired private val inMemoryRoomRepository: InMemoryRoomRepository
) {
    private val currentAccountId: String = "0fab92fd25d64038b5df4c66e240693e"
    private val roomToCreate = object {
        val id: String = "972ed988d71a4c649efbd176be436998"
        val name: String = "sample room"
        val level: Int = 10
        val accountId: String = "0fab92fd25d64038b5df4c66e240693e"
    }

    @BeforeAll
    fun setup() {
        println(">> Setup room application service test")
        inMemoryRoomRepository.clear()
    }

    @Test
    fun `test create room`() {
        val createdRoomId = roomApplicationService.createRoom(
            name = roomToCreate.name,
            level = roomToCreate.level,
            currentAccountId = currentAccountId
        )

        assertTrue(
            inMemoryRoomRepository.findByIdOrNull(
                RoomId(
                    createdRoomId
                )
            ) !== null
        )
    }
}
