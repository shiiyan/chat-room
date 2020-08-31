package com.chatRoom.applicationServiceTests.participantAccount

import com.chatRoom.applicationServices.participantAccount.ParticipantAccountApplicationService
import com.chatRoom.repositories.participantAccount.IParticipantAccountRepository
import com.chatRoom.repositories.participantAccount.InMemoryParticipantAccountRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ParticipantAccountApplicationServiceTest {
    private lateinit var participantAccountRepository: IParticipantAccountRepository
    private lateinit var participantAccountApplicationService: ParticipantAccountApplicationService
    private val participant = object {
        val name: String = "name"
        val iconPath: String = "iconPath"
    }

    @BeforeEach
    fun init() {
        participantAccountRepository = InMemoryParticipantAccountRepository()
        participantAccountApplicationService = ParticipantAccountApplicationService(participantAccountRepository)
    }

    @Test
    fun `test get participantAccount by id`() {
        val id = participantAccountApplicationService.registerParticipantAccount(
            name = participant.name,
            iconPath = participant.iconPath
        )
        val participantAccountDto = participantAccountApplicationService.getParticipantAccountById(id)

        assertEquals(id, participantAccountDto.id)
        assertEquals(participant.name, participantAccountDto.name)
        assertEquals(participant.iconPath, participantAccountDto.iconPath)
    }

    @Test
    fun `test get participantAccount by id not found`() {
        val exception = assertThrows<Exception> {
            participantAccountApplicationService.getParticipantAccountById(id = "account-id-not-exist")
        }
        assertEquals("Participant Account Not Found", exception.message)
    }

    @Test
    fun `test register participantAccount`() {
        participantAccountApplicationService.registerParticipantAccount(
            name = participant.name,
            iconPath = participant.iconPath
        )
    }
}
