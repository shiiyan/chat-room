package com.chatRoom.domainModels.room

import com.chatRoom.IntraAggregateInterfaces.IntraAggregateMessageId
import com.chatRoom.domainModels.participantAccount.AccountId
import java.time.Duration
import java.time.LocalDateTime

class Room(
    val id: RoomId,
    private val name: Name,
    private val level: Level,
    private val participantAccountId: AccountId,
    private val latestMessageList: LatestMessageList,
    private val createdAt: CreatedAt,
    private val updatedAt: UpdatedAt

) {
    companion object {
        fun create(
            name: String,
            level: Int,
            accountId: String
        ) = Room(
            id = RoomId(),
            name = Name(name),
            level = Level(level),
            participantAccountId = AccountId(accountId),
            latestMessageList = LatestMessageList(listOf()),
            createdAt = CreatedAt(LocalDateTime.now()),
            updatedAt = UpdatedAt(LocalDateTime.now())
        )
    }

    fun lowerLevel(number: Int) {
        level.lowerBy(number)
        updatedAt.changeDateTime(LocalDateTime.now())
    }

    fun updateLatestMessageList(newMessageIdList: List<IntraAggregateMessageId>) {
        latestMessageList.updateMessageList(newMessageIdList)
        updatedAt.changeDateTime(LocalDateTime.now())
    }

    fun isQualifiedToEnter(numberOfMessages: Int): Boolean = !level.isHigherThan(numberOfMessages)

    fun isCreatorOfRoom(accountId: String): Boolean = participantAccountId.value == accountId

    fun getSecondsSinceCreated(): Long {
        return Duration.between(createdAt.dateTime, LocalDateTime.now()).seconds
    }

    fun toDto(): RoomDto = RoomDto(
        id = id.value,
        name = name.value,
        level = level.value,
        participantAccountId = participantAccountId.value,
        latestMessageIdList = latestMessageList.value.map { it.messageId },
        createdAt = createdAt.dateTime,
        updatedAt = updatedAt.dateTime
    )
}
