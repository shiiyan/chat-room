package com.chatRoom.domainModels.room.message

import com.chatRoom.extensions.noHyphen
import java.util.UUID

data class MessageId(
    val value: String = UUID.randomUUID().toString().noHyphen()
)
