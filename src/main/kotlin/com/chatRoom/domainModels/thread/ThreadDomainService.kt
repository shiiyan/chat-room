package com.chatRoom.domainModels.thread

import com.chatRoom.domainModels.message.MessageId
import com.chatRoom.repositories.thread.IThreadRepository
import com.chatRoom.repositories.thread.message.IMessageRepository
import com.chatRoom.domainModels.thread.message.MessageId as ThreadMessageId

class ThreadDomainService(
    private val threadRepository: IThreadRepository,
    private val messageRepository: IMessageRepository
) {
    fun createThread(messageId: String): String {
        // TODO: getCurrentAccountId from session yet to implemented
        val currentAccountId = "account-id-not-exist"

        if (threadRepository.findByMessageId(MessageId(messageId)) != null) {
            throw Exception("Thread Already Exists")
        }

        if (messageRepository.findByIdOrNull(ThreadMessageId(messageId)) != null) {
            throw Exception("Thread Not Creatable")
        }

        val threadToCreate = Thread.create(
            accountId = currentAccountId,
            messageId = messageId
        )

        threadRepository.save(threadToCreate)

        return threadToCreate.id.value
    }
}
