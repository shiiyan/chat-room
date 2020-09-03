package com.chatRoom.repositories.message

import com.chatRoom.domainModels.message.Message
import com.chatRoom.domainModels.message.MessageId
import com.chatRoom.domainModels.participantAccount.AccountId
import com.chatRoom.domainModels.room.RoomId
import java.lang.Exception

class InMemoryMessageRepository : IMessageRepository {
    private val data: MutableMap<MessageId, Message> = mutableMapOf()

    override fun findByIdOrNull(messageId: MessageId): Message? = data[messageId]

    override fun findAllByAccountId(accountId: AccountId): List<Message> {
        val filteredData = data.filter {
            (_, value) ->
            value.participantAccountId == accountId
        }

        return filteredData.values.map { it }
    }

    override fun findCountByAccountId(accountId: AccountId): Int {
        val filteredData = data.filter {
            (_, value) ->
            value.participantAccountId == accountId
        }

        return filteredData.values.size
    }

    override fun findLatestByAccountId(accountId: AccountId): List<Message> {
        val filteredData = data.filter {
            (_, value) ->
            value.participantAccountId == accountId
        }

        return filteredData.values.sortedByDescending { it.createdAt.dateTime }
            .take(1)
    }

    override fun findAllByRoomId(roomId: RoomId): List<Message> {
        val filteredData = data.filter {
            (_, value) ->
            value.roomId == roomId
        }

        return filteredData.values.map { it }
    }

    override fun findLatestByRoomId(roomId: RoomId): List<Message> {
        val filteredData = data.filter {
            (_, value) ->
            value.roomId == roomId
        }

        return filteredData.values.sortedByDescending { it.createdAt.dateTime }
            .take(1)
    }

    override fun save(message: Message) {
        data[message.id] = message
    }

    override fun deleteByIdOrFailed(messageId: MessageId) {
        if (data.remove(messageId) == null) {
            throw Exception("Message not found")
        }
    }
}
