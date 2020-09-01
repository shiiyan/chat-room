package com.chatRoom.repositories.room.message

import com.chatRoom.domainModels.participantAccount.AccountId
import com.chatRoom.domainModels.room.RoomId
import com.chatRoom.domainModels.room.message.Message
import com.chatRoom.domainModels.room.message.MessageId

interface IMessageRepository {
    abstract fun findByIdOrNull(messageId: MessageId): Message?
    abstract fun findAllByAccountId(accountId: AccountId): List<Message>
    abstract fun findLatestByAccountId(accountId: AccountId): List<Message>
    abstract fun findAllByRoomId(roomId: RoomId): List<Message>
    abstract fun findLatestByRoomId(roomId: RoomId): List<Message>
    abstract fun save(message: Message)
    abstract fun deleteByIdOrFailed(messageId: MessageId)
}
