package com.chatRoom.applicationServiceTests

import com.chatRoom.applicationServices.ParticipantAccountApplicationService
import com.chatRoom.repositories.InMemoryParticipantAccountRepository
import org.junit.jupiter.api.Test

class ParticipantAccountApplicationServiceTest {
    @Test
    fun `test register participantAccount`() {
        val participantAccountRepository = InMemoryParticipantAccountRepository()
        val participantAccountApplicationService = ParticipantAccountApplicationService(
            participantAccountRepository
        )

        participantAccountApplicationService.registerParticipantAccount(
            name = "name",
            iconPath = "iconPath"
        )
    }
}
