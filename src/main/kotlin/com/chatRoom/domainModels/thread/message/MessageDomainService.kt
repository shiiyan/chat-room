package com.chatRoom.domainModels.thread.message

import com.chatRoom.domainModels.thread.ThreadId
import com.chatRoom.repositories.thread.message.IMessageRepository

class MessageDomainService(
    private val messageRepository: IMessageRepository
) {
    fun sendMessage(text: String, imagePaths: List<String>, threadId: String): String {
        // TODO: getCurrentAccountId from session yet to implemented
        val currentAccountId = "account-id-not-exist"

        if (messageRepository.findAllByThreadId(ThreadId(threadId)).size > 1000) {
            throw Exception("Thread Is Full")
        }

        val messageToSave = Message.create(
            text = text,
            imagePaths = imagePaths,
            accountId = currentAccountId,
            threadId = threadId
        )

        messageRepository.save(messageToSave)

        return messageToSave.id.value
    }
}
