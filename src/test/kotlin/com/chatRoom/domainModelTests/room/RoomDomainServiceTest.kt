package com.chatRoom.domainModelTests.room

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
import java.lang.Exception

class RoomDomainServiceTest {
    private lateinit var roomRepository: IRoomRepository
    private lateinit var messageRepository: IMessageRepository
    private lateinit var roomDomainService: RoomDomainService
    private lateinit var messageDomainService: MessageDomainService
    private val roomToCreate = object {
        val name: String = "name"
        val level: Int = 10
    }
    private val messageToSend = object {
        val text: String = "text"
        val imagePaths: List<String> = listOf("imagePath1", "imagePath2")
    }

    @BeforeEach
    fun init() {
        roomRepository = InMemoryRoomRepository()
        messageRepository = InMemoryMessageRepository()
        roomDomainService = RoomDomainService(roomRepository, messageRepository)
        messageDomainService = MessageDomainService(messageRepository, roomRepository)
    }

    @Test
    fun `test create room successfully`() {
        roomDomainService.createRoom(
            name = roomToCreate.name,
            level = roomToCreate.level
        )
    }

    @Test
    fun `test create room failed by plural rooms`() {
        roomDomainService.createRoom(
            name = roomToCreate.name,
            level = roomToCreate.level
        )
        val exception = assertThrows<Exception> {
            roomDomainService.createRoom(
                name = roomToCreate.name,
                level = roomToCreate.level
            )
        }

        assertEquals("No Right To Create Room", exception.message)
    }

    @Test
    fun `test delete room by creator successfully`() {
        val createdRoomId = roomDomainService.createRoom(
            name = roomToCreate.name,
            level = roomToCreate.level
        )

        roomDomainService.deleteRoom(createdRoomId)

        assertEquals(null, roomRepository.findByIdOrNull(RoomId(createdRoomId)))
    }

//    @Test
//    fun `test delete room by participant successfully`() {
//        // TODO: ("Not Yet Implemented")
//    }

    @Test
    fun `test delete room by creator failed with message in room`() {
        val createdRoomId = roomDomainService.createRoom(
            name = roomToCreate.name,
            level = roomToCreate.level
        )

        messageDomainService.sendMessageByRoomCreator(
            text = messageToSend.text,
            imagePaths = messageToSend.imagePaths,
            roomId = createdRoomId
        )

        val exception = assertThrows<Exception> {
            roomDomainService.deleteRoom(createdRoomId)
        }
        assertEquals("Room Not Deletable", exception.message)
    }

//    @Test
//    fun `test delete room by participant failed with latest message in room`() {}

//    @Test
//    fun `test delete room by participant failed with room created within an hour`() {}
}
