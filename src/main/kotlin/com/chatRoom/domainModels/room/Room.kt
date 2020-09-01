package com.chatRoom.domainModels.room

import com.chatRoom.domainModels.message.Message
import com.chatRoom.domainModels.participantAccount.AccountId
import java.time.LocalDateTime

class Room(
    val id: RoomId,
    private val name: Name,
    private val level: Level,
    val participantAccountId: AccountId,
    private val latestMessageList: LatestMessageList,
    val createdAt: CreatedAt,
    val updatedAt: UpdatedAt

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

    fun updateLatestMessageList(newMessageList: List<Message>) {
        latestMessageList.updateMessageList(newMessageList)
        updatedAt.changeDateTime(LocalDateTime.now())
    }

    fun isQualifiedToEnter(numberOfMessages: Int): Boolean = !level.isHigherThan(numberOfMessages)

    fun toDto(): RoomDto = RoomDto(
        id = id.value,
        name = name.value,
        level = level.value,
        participantAccountId = participantAccountId.value,
        createdAt = createdAt.dateTime,
        updatedAt = updatedAt.dateTime
    )
}
