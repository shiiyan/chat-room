package com.chatroom.app.dddImplement.domain.room

import com.chatroom.app.dddImplement.domain.participantAccount.AccountId

class Room(
    val id: RoomId,
    private val name: Name,
    private val level: Level,
    private val participantAccountId: AccountId

) {
    companion object {
        fun create(
            id: RoomId,
            name: Name,
            level: Level,
            accountId: AccountId
        ) = Room(
            id = id,
            name = name,
            level = level,
            participantAccountId = accountId
        )
    }

//    fun lowerLevel(number: Int) {
//        level.lowerBy(number)
//        updatedAt.changeDateTime(LocalDateTime.now())
//    }
//
//    fun updateLatestMessageList(newMessageIdList: List<IntraAggregateMessageId>) {
//        latestMessageList.updateMessageList(newMessageIdList)
//        updatedAt.changeDateTime(LocalDateTime.now())
//    }
//
//    fun isQualifiedToEnter(numberOfMessages: Int): Boolean = !level.isHigherThan(numberOfMessages)
//
//    fun isCreatorOfRoom(accountId: String): Boolean = participantAccountId.value == accountId
//
//    fun getSecondsSinceCreated(): Long {
//        return Duration.between(createdAt.dateTime, LocalDateTime.now()).seconds
//    }

    fun toDto(): RoomDto = RoomDto(
        id = id.value,
        name = name.value,
        level = level.value,
        participantAccountId = participantAccountId.value
    )
}
