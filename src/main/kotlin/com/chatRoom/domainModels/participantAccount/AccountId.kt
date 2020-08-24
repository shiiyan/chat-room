package com.chatRoom.domainModels.participantAccount

import com.chatRoom.extensions.noHyphen
import java.util.UUID

data class AccountId(
    val value: String = UUID.randomUUID().toString().noHyphen()
)
