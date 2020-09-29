package com.chatroom.app.dddImplement.persistence.message

import com.chatroom.app.dddImplement.domain.participantAccount.AccountId
import com.chatroom.app.dddImplement.domain.room.RoomId
import com.chatroom.ddd.domainModels.message.Message
import com.chatroom.ddd.domainModels.message.MessageId

interface MessageRepository {
    abstract fun generateNextIdentity(): MessageId
    abstract fun findByIdOrNull(messageId: MessageId): Message?
    abstract fun findAllByAccountId(accountId: AccountId): List<Message>
    abstract fun findCountByAccountId(accountId: AccountId): Int
    abstract fun findLatestByAccountId(accountId: AccountId): Message?
    abstract fun findAllByRoomId(roomId: RoomId): List<Message>
    abstract fun findLatestByRoomId(roomId: RoomId): Message?
    abstract fun save(message: Message)
    abstract fun deleteByIdOrFailed(messageId: MessageId)
}
