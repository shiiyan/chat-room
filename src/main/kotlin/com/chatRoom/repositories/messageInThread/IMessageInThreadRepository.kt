package com.chatRoom.repositories.messageInThread

import com.chatRoom.domainModels.thread.ThreadId
import com.chatRoom.domainModels.message.MessageId
import com.chatRoom.domainModels.messageInThread.MessageInThread

interface IMessageInThreadRepository {
    abstract fun findByIdOrNull(messageId: MessageId): MessageInThread?
    abstract fun findAllByThreadId(threadId: ThreadId): List<MessageInThread>
    abstract fun findLatestByThreadId(threadId: ThreadId): List<MessageInThread>
    abstract fun save(messageInThread: MessageInThread)
}
