package com.chatRoom.domainModels.thread

// message in thread
import com.chatRoom.domainModels.room.MessageListItem

data class LatestMessageList(val value: List<MessageListItem>)
