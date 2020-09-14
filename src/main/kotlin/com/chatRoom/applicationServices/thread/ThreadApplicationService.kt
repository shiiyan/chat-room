package com.chatRoom.applicationServices.thread

import com.chatRoom.domainModels.message.MessageId
import com.chatRoom.domainModels.thread.Thread
import com.chatRoom.repositories.thread.IThreadRepository
import kotlin.concurrent.thread

class ThreadApplicationService(
    private val threadRepository: IThreadRepository

) {
    fun sendThread(text: String, imagePaths: List<String>, messageId: String): String {
        // TODO: getCurrentAccountId from session yet to implemented
        val currentAccountId = "account-id-not-exist"

        if (threadRepository.findAllByMessageId(MessageId(messageId)).size > 1000) {
            throw Exception("Thread Not Savable")
        }

        val threadToSave = Thread.create(
            text = text,
            imagePaths = imagePaths,
            accountId = currentAccountId,
            messageId = messageId
        )

        threadRepository.save(threadToSave)

        return threadToSave.id.value
    }
}
