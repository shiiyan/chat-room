package com.chatRoom.domainModelTests.message

import com.chatRoom.domainModels.message.MessageDomainService
import com.chatRoom.domainModels.room.RoomDomainService
import com.chatRoom.domainModels.room.RoomId
import com.chatRoom.repositories.message.IMessageRepository
import com.chatRoom.repositories.message.InMemoryMessageRepository
import com.chatRoom.repositories.room.IRoomRepository
import com.chatRoom.repositories.room.InMemoryRoomRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MessageDomainServiceTest {
    private lateinit var messageRepository: IMessageRepository
    private lateinit var roomRepository: IRoomRepository
    private lateinit var messageDomainService: MessageDomainService
    private lateinit var roomDomainService: RoomDomainService
    private val sampleRoom = object {
        val name: String = "sample-room"
        val level: Int = 1
    }
    private val sampleMessage = object {
        val text: String = "text"
        val imagePaths: List<String> = listOf("imagePath1", "imagePath2")
    }
    private val editedSampleMessage = object {
        val text: String = "newText"
        val imagePaths: List<String> = listOf("newImagePath1", "newImagePath2")
    }

    @BeforeEach
    fun init() {
        messageRepository = InMemoryMessageRepository()
        roomRepository = InMemoryRoomRepository()
        messageDomainService = MessageDomainService(messageRepository, roomRepository)
        roomDomainService = RoomDomainService(roomRepository, messageRepository)
    }

    @Test
    fun `test send message by room creator successfully`() {
        val sampleRoomId = roomDomainService.createRoom(
            name = sampleRoom.name,
            level = sampleRoom.level
        )
        val sampleMessageId = messageDomainService.sendMessageByRoomCreator(
            text = sampleMessage.text,
            imagePaths = sampleMessage.imagePaths,
            roomId = sampleRoomId
        )
        val foundRoom = roomRepository.findByIdOrNull(RoomId(sampleRoomId))!!

        assertEquals(sampleMessageId, foundRoom.toDto().latestMessageIdList.first())
    }

    @Test
    fun `test send message by room creator failed with room not found`() {
        assertThrows<NullPointerException> {
            messageDomainService.sendMessageByRoomCreator(
                text = sampleMessage.text,
                imagePaths = sampleMessage.imagePaths,
                roomId = "room-id-not-exist"
            )
        }
    }

    @Test
    fun `test edit message successfully`() {
        val sampleRoomId = roomDomainService.createRoom(
            name = sampleRoom.name,
            level = sampleRoom.level
        )
        val sampleMessageId = messageDomainService.sendMessageByRoomCreator(
            text = sampleMessage.text,
            imagePaths = sampleMessage.imagePaths,
            roomId = sampleRoomId
        )
        messageDomainService.editMessage(
            messageId = sampleMessageId,
            newText = editedSampleMessage.text,
            newImagePaths = editedSampleMessage.imagePaths
        )
    }

    @Test
    fun `test edit message failed with message not found`() {
        assertThrows<NullPointerException> {
            messageDomainService.editMessage(
                messageId = "message-id-not-exists",
                newText = editedSampleMessage.text,
                newImagePaths = editedSampleMessage.imagePaths
            )
        }
    }

//    @Test
//    fun `test edit message failed with no right to edit`() {
//        TODO("Not Yet Implemented")
//    }
}
