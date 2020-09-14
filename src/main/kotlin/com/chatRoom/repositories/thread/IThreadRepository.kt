package com.chatRoom.repositories.thread

import com.chatRoom.domainModels.message.MessageId
import com.chatRoom.domainModels.thread.Thread
import com.chatRoom.domainModels.thread.ThreadId

interface IThreadRepository {
    abstract fun findByIdOrNull(threadId: ThreadId): Thread?
    abstract fun findByMessageId(messageId: MessageId): Thread?
    abstract fun save(thread: Thread)
}
