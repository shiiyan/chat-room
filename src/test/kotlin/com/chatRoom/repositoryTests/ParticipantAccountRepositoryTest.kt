package com.chatRoom.repositoryTests

import com.chatRoom.domainModels.participantAccount.ParticipantAccount
import com.chatRoom.repositories.IParticipantAccountRepository
import com.chatRoom.repositories.InMemoryParticipantAccountRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ParticipantAccountRepositoryTest() {
    private lateinit var participantAccountRepository: IParticipantAccountRepository
    private val participantAccount1: ParticipantAccount = ParticipantAccount.create(name = "name1", iconPath = "iconPath1")
    private val participantAccount2: ParticipantAccount = ParticipantAccount.create(name = "name2", iconPath = "iconPath2")

    @BeforeEach
    fun init() {
        participantAccountRepository = InMemoryParticipantAccountRepository()
        participantAccountRepository.save(participantAccount1)
    }

    @Test
    fun `test find participantAccount by Id`() {
        val foundParticipantAccount = participantAccountRepository.findByIdOrNull(participantAccount1.id)
        assertEquals(participantAccount1, foundParticipantAccount)
    }

    @Test
    fun `test save participantAccount`() {
        participantAccountRepository.save(participantAccount2)
        val foundAllParticipantAccount = participantAccountRepository.findAll()
        assertEquals(2, foundAllParticipantAccount.size)
        assertTrue(foundAllParticipantAccount.contains(participantAccount2))
    }
}
