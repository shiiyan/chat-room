package com.chatRoom.repositories.message

import com.chatRoom.domainModels.message.Message
import com.chatRoom.domainModels.message.MessageId
import com.chatRoom.domainModels.participantAccount.AccountId
import com.chatRoom.domainModels.room.RoomId

interface IMessageRepository {
    abstract fun findByIdOrNull(messageId: MessageId): Message?
    abstract fun findAllByAccountId(accountId: AccountId): List<Message>
    abstract fun findCountByAccountId(accountId: AccountId): Int
    abstract fun findLatestByAccountId(accountId: AccountId): List<Message>
    abstract fun findAllByRoomId(roomId: RoomId): List<Message>
    abstract fun findLatestByRoomId(roomId: RoomId): List<Message>
    abstract fun save(messageInThread: Message)
    abstract fun deleteByIdOrFailed(messageId: MessageId)
}
