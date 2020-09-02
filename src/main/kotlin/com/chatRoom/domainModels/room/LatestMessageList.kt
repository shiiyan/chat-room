package com.chatRoom.domainModels.room

import com.chatRoom.IntraAggregateInterfaces.IntraAggregateMessageId

// message for room

data class LatestMessageList(var value: List<MessageListItem>) {
    fun updateMessageList(newMessageIdList: List<IntraAggregateMessageId>) {

        value = newMessageIdList.map { MessageListItem(it.messageId) }
    }
}
