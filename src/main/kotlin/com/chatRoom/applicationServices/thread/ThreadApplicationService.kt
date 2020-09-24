package com.chatRoom.applicationServices.thread

import com.chatRoom.domainModels.thread.ThreadDomainService
import com.chatRoom.repositories.messageInThread.IMessageInThreadRepository
import com.chatRoom.repositories.thread.IThreadRepository

class ThreadApplicationService(
    private val threadRepository: IThreadRepository,
    private val messageInThreadRepository: IMessageInThreadRepository

) {
    fun createThread(rootMessageId: String): String =
        ThreadDomainService(threadRepository, messageInThreadRepository).createThread(rootMessageId)

    fun postMessage(text: String, imagePaths: List<String>, threadId: String): String =
        ThreadDomainService(threadRepository, messageInThreadRepository).postMessage(
            text, imagePaths, threadId
        )
}
