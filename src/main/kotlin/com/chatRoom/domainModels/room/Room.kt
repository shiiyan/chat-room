package com.chatRoom.domainModels.room

import com.chatRoom.domainModels.participantAccount.AccountId

class Room(
    val id: RoomId,
    private val name: Name,
    private val level: Level,
    val participantAccountId: AccountId
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
            participantAccountId = AccountId(accountId)
        )
    }

    fun lowerLevel(lowerBy: Int) {
        level.lower(lowerBy)
    }

    fun toDto(): RoomDto = RoomDto(
        id = id.value,
        name = name.value,
        level = level.value,
        participantAccountId = participantAccountId.value
    )
}
