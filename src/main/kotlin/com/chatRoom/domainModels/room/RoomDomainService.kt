package com.chatRoom.domainModels.room

import com.chatRoom.domainModels.participantAccount.AccountId
import com.chatRoom.repositories.room.IRoomRepository

class RoomDomainService(private val roomRepository: IRoomRepository) {
    fun createRoom(name: String, level: Int, accountId: String): String {
        // update method to check whether the participant is able to create room
        // 参加者は最初「ルーム」を1つしか作成することができないが、「メッセージ」を1回でも送信すると、無制限に作成できるようになる。
        val createdRoom = roomRepository.findByAccountId(AccountId(accountId))
        if (createdRoom.isNotEmpty()) {
            throw Exception("Plural Rooms Forbidden")
        }

        val room = Room.create(name = name, level = level, accountId = accountId)
        roomRepository.save(room)

        return room.id.value
    }
}
