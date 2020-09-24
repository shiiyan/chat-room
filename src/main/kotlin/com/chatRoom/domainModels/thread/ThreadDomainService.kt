package com.chatRoom.domainModels.thread

import com.chatRoom.domainModels.message.MessageId
import com.chatRoom.domainModels.messageInThread.MessageInThread
import com.chatRoom.repositories.messageInThread.IMessageInThreadRepository
import com.chatRoom.repositories.thread.IThreadRepository
import com.chatRoom.domainModels.message.MessageId as ThreadMessageId

class ThreadDomainService(
    private val threadRepository: IThreadRepository,
    private val messageInThreadRepository: IMessageInThreadRepository
) {
    fun createThread(rootMessageId: String): String {
        // TODO: getCurrentAccountId from session yet to implemented
        val currentAccountId = "account-id-not-exist"

        if (threadRepository.findByMessageId(MessageId(rootMessageId)) != null) {
            throw Exception("Thread Already Exists")
        }

        if (messageInThreadRepository.findByIdOrNull(ThreadMessageId(rootMessageId)) != null) {
            throw Exception("Thread Not Creatable")
        }

        val threadToCreate = Thread.create(
            accountId = currentAccountId,
            rootMessageId = rootMessageId
        )

        threadRepository.save(threadToCreate)

        return threadToCreate.id.value
    }

    fun postMessage(text: String, imagePaths: List<String>, threadId: String): String {
        val targetThread = threadRepository.findByIdOrNull(ThreadId(threadId)) ?: throw Exception("Thread Not Found")

        if (messageInThreadRepository.findAllByThreadId(targetThread.id).size > 1000) {
            throw Exception("Thread Is Full")
        }

        // TODO: getCurrentAccountId from session yet to implemented
        val currentAccountId = "account-id-not-exist"

        val messageToSave = MessageInThread.create(
            text = text,
            imagePaths = imagePaths,
            accountId = currentAccountId,
            threadId = threadId
        )

        targetThread.updateLatestMessageList(messageToSave.id.value)

        messageInThreadRepository.save(messageToSave)
        threadRepository.save(targetThread)

        return messageToSave.id.value
    }
}
