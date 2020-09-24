package com.chatRoom.domainModels.messageInThread

import com.chatRoom.domainModels.participantAccount.AccountId
import com.chatRoom.domainModels.thread.ThreadId
import java.time.LocalDateTime

class MessageInThread(
        val id: MessageId,
        private val text: Text,
        private val image: Image,
        val participantAccountId: AccountId,
        val threadId: ThreadId,
        val createdAt: CreatedAt,
        val updatedAt: UpdatedAt
) {
    companion object {
        fun create(
                text: String,
                imagePaths: List<String>,
                accountId: String,
                threadId: String
        ) = MessageInThread(
                id = MessageId(),
                text = Text(text),
                image = Image(imagePaths),
                participantAccountId = AccountId(accountId),
                threadId = ThreadId(threadId),
                createdAt = CreatedAt(LocalDateTime.now()),
                updatedAt = UpdatedAt(LocalDateTime.now())
        )
    }

    fun toDto(): MessageInThreadDto = MessageInThreadDto(
            id = id.value,
            text = text.value,
            image = image.pathList,
            participantAccountId = participantAccountId.value,
            threadId = threadId.value,
            createdAt = createdAt.dateTime,
            updatedAt = updatedAt.dateTime
    )
}
