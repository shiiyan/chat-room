package com.chatRoom.applicationServices

import com.chatRoom.domainModels.participantAccount.ParticipantAccount
import com.chatRoom.repositories.IParticipantAccountRepository

class ParticipantAccountApplicationService(private val participantAccountRepository: IParticipantAccountRepository) {
    fun registerParticipantAccount(name: String, iconPath: String) {
        val participantAccount = ParticipantAccount.create(name, iconPath)
        participantAccountRepository.save(participantAccount)
    }
}
