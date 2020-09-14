package com.chatRoom.applicationServices.thread

import com.chatRoom.domainModels.message.MessageId
import com.chatRoom.domainModels.thread.ThreadDomainService
import com.chatRoom.repositories.thread.IThreadRepository
import kotlin.concurrent.thread

class ThreadApplicationService(
    private val threadRepository: IThreadRepository

) {
    fun createThread(messageId: String): String =
        ThreadDomainService(threadRepository).createThread(messageId)

    fun getThreadId(messageId: String): String? = threadRepository.findByMessageId(
        MessageId(messageId)
    )?.id?.value
}
