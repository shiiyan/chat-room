package com.chatRoom.domainModelTests

import com.chatRoom.domainModels.participantAccount.ParticipantAccount
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ParticipantAccountTest {
    @Test
    fun `test create participant account  successfully`() {
        ParticipantAccount.create(name = "name", iconPath = "iconPath")
    }

    @Test
    fun `test create participant account failed with name too short`() {
        assertThrows<IllegalArgumentException> {
            ParticipantAccount.create(name = "", iconPath = "iconPath")
        }
    }

    @Test
    fun `test create partipant account failed with name too long`() {
        assertThrows<java.lang.IllegalArgumentException> {
            ParticipantAccount.create(name = "namenamenamenamename", iconPath = "iconPath")
        }
    }
}
