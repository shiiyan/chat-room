package com.chatroom.app.dddImplementTest.domainTest.roomTest

import com.chatroom.app.dddImplement.domain.participantAccount.AccountId
import com.chatroom.app.dddImplement.domain.room.Level
import com.chatroom.app.dddImplement.domain.room.Name
import com.chatroom.app.dddImplement.domain.room.Room
import com.chatroom.app.dddImplement.domain.room.RoomId
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RoomTest {
    private val roomToCreate = object {
        val id: String = "972ed988d71a4c649efbd176be436998"
        val name: String = "sample room"
        val level: Int = 10
        val accountId: String = "0fab92fd25d64038b5df4c66e240693e"
    }

    @Test
    fun `test create room successfully`() {
        val createdRoom = Room.create(
            id = RoomId(roomToCreate.id),
            name = Name(roomToCreate.name),
            level = Level(roomToCreate.level),
            accountId = AccountId(roomToCreate.accountId)
        )
        assertEquals(roomToCreate.id, createdRoom.id.value)
    }
}
