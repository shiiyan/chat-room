package com.chatroom.ddd.domainModels.participantAccount

import com.chatroom.app.dddImplement.domain.participantAccount.AccountId

class ParticipantAccount(
    val id: AccountId,
    private val name: Name,
    private val iconPath: IconPath
) {
    companion object {
        fun create(
            id: AccountId,
            name: Name,
            iconPath: IconPath
        ) = ParticipantAccount(
            id = id,
            name = name,
            iconPath = iconPath
        )
    }

    fun toDto(): ParticipantAccountDto = ParticipantAccountDto(
        id = id.value,
        name = name.value,
        iconPath = iconPath.value
    )
}
