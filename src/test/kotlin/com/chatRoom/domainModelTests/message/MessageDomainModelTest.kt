package com.chatRoom.domainModelTests.message

import com.chatRoom.domainModels.message.CreatedAt
import com.chatRoom.domainModels.message.Image
import com.chatRoom.domainModels.message.Message
import com.chatRoom.domainModels.message.MessageId
import com.chatRoom.domainModels.message.Text
import com.chatRoom.domainModels.message.UpdatedAt
import com.chatRoom.domainModels.participantAccount.AccountId
import com.chatRoom.domainModels.room.RoomId
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import java.time.LocalDateTime

class MessageDomainModelTest {
    @Test
    fun `test create message successfully`() {
        Message.create(
            text = "text",
            imagePaths = listOf("imagePath1", "imagePath2"),
            accountId = "sample-account-id",
            roomId = "sample-room-id"
        )
    }

    @Test
    fun `test create message failed with text too short`() {
        assertThrows<IllegalArgumentException> {
            Message.create(
                text = "",
                imagePaths = listOf("imagePath1", "imagePath2"),
                accountId = "sample-account-id",
                roomId = "sample-room-id"
            )
        }
    }

    @Test
    fun `test create message failed with text too long`() {
        assertThrows<IllegalArgumentException> {
            Message.create(
                text = "a".repeat(1001),
                imagePaths = listOf("imagePath1", "imagePath2"),
                accountId = "sample-account-id",
                roomId = "sample-room-id"
            )
        }
    }

    @Test
    fun `test create message failed with imagePath too short`() {
        assertThrows<IllegalArgumentException> {
            Message.create(
                text = "text",
                imagePaths = listOf("", "imagePath2"),
                accountId = "sample-account-id",
                roomId = "sample-room-id"
            )
        }
    }

    @Test
    fun `test create message failed with accountId too short`() {
        assertThrows<IllegalArgumentException> {
            Message.create(
                text = "text",
                imagePaths = listOf("imagePath1", "imagePath2"),
                accountId = "",
                roomId = "sample-room-id"
            )
        }
    }

    @Test
    fun `test create message failed with roomId too short`() {
        assertThrows<IllegalArgumentException> {
            Message.create(
                text = "text",
                imagePaths = listOf("imagePath1", "imagePath2"),
                accountId = "sample-account-id",
                roomId = ""
            )
        }
    }

    @Test
    fun `update message text successfully`() {
        val sampleMessage = Message.create(
            text = "text",
            imagePaths = listOf("imagePath1", "imagePath2"),
            accountId = "sample-account-id",
            roomId = "sample-room-id"
        )

        sampleMessage.updateText("newText")
    }

    @Test
    fun `update message text failed with message created too early`() {
        val sampleMessage = Message(
            id = MessageId(),
            text = Text("text"),
            image = Image(listOf("imagePath1", "imagePath2")),
            participantAccountId = AccountId("sample-account-id"),
            roomId = RoomId("sample-room-id"),
            createdAt = CreatedAt(LocalDateTime.now().minusHours(1)),
            updatedAt = UpdatedAt(LocalDateTime.now().minusHours(1))
        )

        val exception = assertThrows<Exception> {
            sampleMessage.updateText("newText")
        }
        assertEquals("Message Not Editable", exception.message)
    }

    @Test
    fun `update message image successfully`() {
        val sampleMessage = Message.create(
            text = "text",
            imagePaths = listOf("imagePath1", "imagePath2"),
            accountId = "sample-account-id",
            roomId = "sample-room-id"
        )

        sampleMessage.updateImage(listOf("imagePath3", "imagePath2"))
    }

    @Test
    fun `update message image failed with message created too early`() {
        val sampleMessage = Message(
            id = MessageId(),
            text = Text("text"),
            image = Image(listOf("imagePath1", "imagePath2")),
            participantAccountId = AccountId("sample-account-id"),
            roomId = RoomId("sample-room-id"),
            createdAt = CreatedAt(LocalDateTime.now().minusHours(1)),
            updatedAt = UpdatedAt(LocalDateTime.now().minusHours(1))
        )

        val exception = assertThrows<Exception> {
            sampleMessage.updateImage(listOf("imagePath3", "imagePath2"))
        }
        assertEquals("Message Not Editable", exception.message)
    }

    @Test
    fun `test is creator of message`() {
        val sampleMessage = Message.create(
            text = "text",
            imagePaths = listOf("imagePath1", "imagePath2"),
            accountId = "sample-account-id",
            roomId = "sample-room-id"
        )

        assertFalse(sampleMessage.isCreatorOfMessage("sample-account-id-2"))
    }
}
