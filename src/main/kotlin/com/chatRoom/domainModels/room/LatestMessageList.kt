package com.chatRoom.domainModels.room

import com.chatRoom.domainModels.message.Message

data class LatestMessageList(var value: List<Message>) {
    fun updateMessageList(newMessageList: List<Message>) {
        value = newMessageList
    }
}
