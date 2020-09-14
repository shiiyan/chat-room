package com.chatRoom.applicationServices.thread

import com.chatRoom.domainModels.message.MessageId
import com.chatRoom.domainModels.thread.ThreadDomainService
import com.chatRoom.repositories.thread.IThreadRepository
import com.chatRoom.repositories.thread.message.IMessageRepository
import kotlin.concurrent.thread

class ThreadApplicationService(
    private val threadRepository: IThreadRepository,
    private val messageRepository: IMessageRepository

) {
    fun createThread(messageId: String): String =
        ThreadDomainService(threadRepository, messageRepository).createThread(messageId)

    fun getThreadId(messageId: String): String? = threadRepository.findByMessageId(
        MessageId(messageId)
    )?.id?.value
}
