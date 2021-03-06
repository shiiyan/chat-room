package com.chatRoom.domainModels.room

import com.chatRoom.intraAggregateDataClasses.IntraAggregateMessageId

// message in room

data class LatestMessageList(var value: List<MessageListItem>) {
    fun updateMessageList(newMessageIdList: List<IntraAggregateMessageId>) {

        value = newMessageIdList.map { MessageListItem(it.messageId) }
    }
}
