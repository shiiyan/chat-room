package com.chatRoom.repositoryTests.message

import com.chatRoom.domainModels.message.Message
import com.chatRoom.domainModels.participantAccount.AccountId
import com.chatRoom.domainModels.room.RoomId
import com.chatRoom.repositories.message.IMessageRepository
import com.chatRoom.repositories.message.InMemoryMessageRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MessageRepositoryTest {
    private lateinit var messageRepository: IMessageRepository
    private val sampleAccountId = "sample-account-id"
    private val sampleRoomId = "sample-room-id"
    private val message1: Message = Message.create(
        text = "text1",
        imagePaths = listOf("imagePath1"),
        accountId = sampleAccountId,
        roomId = sampleRoomId
    )
    private val message2: Message = Message.create(
        text = "text2",
        imagePaths = listOf("imagePath2"),
        accountId = sampleAccountId,
        roomId = sampleRoomId
    )

    @BeforeEach
    fun init() {
        messageRepository = InMemoryMessageRepository()
        messageRepository.save(message1)
    }

    @Test
    fun `test find message by id`() {
        messageRepository.findByIdOrNull(message1.id)
    }

    @Test
    fun `test find all by account id`() {
        val foundAll = messageRepository.findAllByAccountId(AccountId(sampleAccountId))
        assertEquals(1, foundAll.size)
        assertTrue(foundAll.contains(message1))
    }

    @Test
    fun `test find latest by account id`() {
        val foundLatest = messageRepository.findLatestByAccountId(AccountId(sampleAccountId))
        assertEquals(1, foundLatest.size)
        assertTrue(foundLatest.contains(message1))
    }

    @Test
    fun `test find all by room id`() {
        val foundAll = messageRepository.findAllByRoomId(RoomId(sampleRoomId))
        assertEquals(1, foundAll.size)
        assertTrue(foundAll.contains(message1))
    }

    @Test
    fun `test find latest by room id`() {
        val foundLatest = messageRepository.findLatestByRoomId(RoomId(sampleRoomId))
        assertEquals(1, foundLatest.size)
        assertTrue(foundLatest.contains(message1))
    }

    @Test
    fun `test save message`() {
        messageRepository.save(message2)
        val foundAllInRoom = messageRepository.findAllByRoomId(RoomId(sampleRoomId))
        assertEquals(2, foundAllInRoom.size)
        assertTrue(foundAllInRoom.contains(message1))
        assertTrue(foundAllInRoom.contains(message2))
    }

    @Test
    fun `test delete message by id successfully`() {
        messageRepository.deleteByIdOrFailed(message1.id)
        val foundMessage = messageRepository.findByIdOrNull(message1.id)
        assertEquals(null, foundMessage)
    }

    @Test
    fun `test delete message by id failed with wrong id`() {
        val exception = assertThrows<Exception> {
            messageRepository.deleteByIdOrFailed(message2.id)
        }
        assertEquals("Message not found", exception.message)
    }
}
