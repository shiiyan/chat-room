package com.chatroom.app.dddImplement.port.persistence.message

import com.chatroom.app.commonConcern.noHyphen
import com.chatroom.app.dddImplement.domain.participantAccount.AccountId
import com.chatroom.app.dddImplement.domain.room.RoomId
import com.chatroom.ddd.domainModels.message.Message
import com.chatroom.ddd.domainModels.message.MessageId
import org.springframework.stereotype.Repository
import java.lang.IllegalArgumentException
import java.util.UUID

@Repository
class InMemoryMessageRepository : MessageRepository {
    private val data: MutableList<Message> = mutableListOf()

    override fun generateNextIdentity(): MessageId = MessageId(
        UUID.randomUUID().toString().noHyphen()
    )

    override fun findByIdOrNull(messageId: MessageId): Message? = data.find {
        it.id == messageId
    }

    override fun findAllByAccountId(accountId: AccountId): List<Message> = data.filter {
        AccountId(it.toDto().participantAccountId) == accountId
    }

    override fun findCountByAccountId(accountId: AccountId): Int = data.filter {
        AccountId(it.toDto().participantAccountId) == accountId
    }.size

    override fun findLatestByAccountId(accountId: AccountId): Message? = data.findLast {
        AccountId(it.toDto().participantAccountId) == accountId
    }

    override fun findAllByRoomId(roomId: RoomId): List<Message> = data.filter {
        RoomId(it.toDto().roomId) == roomId
    }

    override fun findLatestByRoomId(roomId: RoomId): Message? = data.findLast {
        RoomId(it.toDto().roomId) == roomId
    }

    override fun save(message: Message) {
        data.add(message)
    }

    override fun deleteByIdOrFailed(messageId: MessageId) {
        if (findByIdOrNull(messageId) == null) {
            throw IllegalArgumentException("Message Not Found")
        }

        data.removeIf { it.id == messageId }
    }
}
