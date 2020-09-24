package com.chatRoom.domainModels.thread

import com.chatRoom.domainModels.message.MessageId
import com.chatRoom.domainModels.participantAccount.AccountId
import com.chatRoom.domainModels.room.MessageListItem
import java.time.LocalDateTime

class Thread(
    val id: ThreadId,
    private var latestMessageList: LatestMessageList,
    private val participantAccountId: AccountId,
    private val rootMessageId: MessageId,
    private val createdAt: CreatedAt,
    private var updatedAt: UpdatedAt
) {
    companion object {
        fun create(
            accountId: String,
            rootMessageId: String
        ) = Thread(
            id = ThreadId(),
            latestMessageList = LatestMessageList(listOf()),
            participantAccountId = AccountId(accountId),
            rootMessageId = MessageId(rootMessageId),
            createdAt = CreatedAt(LocalDateTime.now()),
            updatedAt = UpdatedAt(LocalDateTime.now())
        )
    }

    fun updateLatestMessageList(newMessageId: String) {
        val updatedMessageList = LatestMessageList(
            listOf(MessageListItem((newMessageId)))
        )

        latestMessageList = updatedMessageList
        updatedAt = UpdatedAt(LocalDateTime.now())
    }

    fun toDto(): ThreadDto = ThreadDto(
        id = id.value,
        latestMessageList = latestMessageList.value.map { it.messageId },
        participantAccountId = participantAccountId.value,
        rootMessageId = rootMessageId.value,
        createdAt = createdAt.dateTime,
        updatedAt = updatedAt.dateTime
    )
}
