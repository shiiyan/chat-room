package com.chatRoom.domainModels.participantAccount

class ParticipantAccount(
    val id: AccountId,
    private val name: Name,
    private val icon: Icon
) {
    companion object {
        fun create(
            name: String,
            iconPath: String
        ) = ParticipantAccount(
            id = AccountId(),
            name = Name(name),
            icon = Icon(iconPath)
        )
    }
}
