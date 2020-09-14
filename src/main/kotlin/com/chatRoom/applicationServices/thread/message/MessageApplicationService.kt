package com.chatRoom.applicationServices.thread.message

import com.chatRoom.domainModels.thread.message.MessageDomainService
import com.chatRoom.repositories.thread.message.IMessageRepository

class MessageApplicationService(
    private val messageRepository: IMessageRepository
) {

    fun sendMessage(text: String, imagePaths: List<String>, threadId: String) =
        MessageDomainService(messageRepository).sendMessage(text, imagePaths, threadId)
}
