package com.chatRoom.applicationServices.thread

import com.chatRoom.domainModels.message.MessageId
import com.chatRoom.domainModels.thread.Thread
import com.chatRoom.repositories.thread.IThreadRepository
import kotlin.concurrent.thread

class ThreadApplicationService(
    private val threadRepository: IThreadRepository

) {
    fun createThread(messageId: String): String {
        // TODO: getCurrentAccountId from session yet to implemented
        val currentAccountId = "account-id-not-exist"

        if (threadRepository.findByMessageId(MessageId(messageId)) != null) {
            throw Exception("Thread Not Creatable")
        }

        val threadToCreate = Thread.create(
            accountId = currentAccountId,
            messageId = messageId
        )

        threadRepository.save(threadToCreate)

        return threadToCreate.id.value
    }

    fun getThreadId(messageId: String): String? = threadRepository.findByMessageId(
        MessageId(messageId)
    )?.id?.value
}
