package com.chatRoom.applicationServiceTests.message

import com.chatRoom.applicationServices.message.MessageApplicationService
import com.chatRoom.applicationServices.room.RoomApplicationService
import com.chatRoom.repositories.message.IMessageRepository
import com.chatRoom.repositories.message.InMemoryMessageRepository
import com.chatRoom.repositories.room.IRoomRepository
import com.chatRoom.repositories.room.InMemoryRoomRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MessageApplicationServiceTest {
    private lateinit var messageRepository: IMessageRepository
    private lateinit var roomRepository: IRoomRepository
    private lateinit var messageApplicationService: MessageApplicationService
    private lateinit var roomApplicationService: RoomApplicationService
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
        roomRepository = InMemoryRoomRepository()
        messageRepository = InMemoryMessageRepository()
        messageApplicationService = MessageApplicationService(messageRepository, roomRepository)
        roomApplicationService = RoomApplicationService(roomRepository, messageRepository)
    }

    @Test
    fun `test send message`() {
        val sampleRoomId = roomApplicationService.createRoom(
            name = sampleRoom.name,
            level = sampleRoom.level
        )

        messageApplicationService.sendMessage(
            text = sampleMessage.text,
            imagePaths = sampleMessage.imagePaths,
            roomId = sampleRoomId
        )
    }

    @Test
    fun `test edit message`() {
        val sampleRoomId = roomApplicationService.createRoom(
            name = sampleRoom.name,
            level = sampleRoom.level
        )

        val sampleMessageId = messageApplicationService.sendMessage(
            text = sampleMessage.text,
            imagePaths = sampleMessage.imagePaths,
            roomId = sampleRoomId
        )

        messageApplicationService.editMessage(
            messageId = sampleMessageId,
            newText = editedSampleMessage.text,
            newImagePaths = editedSampleMessage.imagePaths
        )
    }
}
