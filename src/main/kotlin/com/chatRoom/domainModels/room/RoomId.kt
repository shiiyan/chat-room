package com.chatRoom.domainModels.room

import com.chatRoom.extensions.noHyphen
import java.util.UUID

data class RoomId(
    val value: String = UUID.randomUUID().toString().noHyphen()
)
