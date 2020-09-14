package com.chatRoom.repositories.thread.message

import com.chatRoom.domainModels.thread.ThreadId
import com.chatRoom.domainModels.thread.message.Message
import com.chatRoom.domainModels.thread.message.MessageId

interface IMessageRepository {
    abstract fun findByIdOrNull(messageId: MessageId): Message?
    abstract fun findAllByThreadId(threadId: ThreadId): List<Message>
    abstract fun findLatestByThreadId(threadId: ThreadId): List<Message>
    abstract fun save(message: Message)
}
