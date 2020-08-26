package com.chatRoom.applicationServices

import com.chatRoom.domainModels.participantAccount.AccountId
import com.chatRoom.domainModels.participantAccount.ParticipantAccount
import com.chatRoom.domainModels.participantAccount.ParticipantAccountDto
import com.chatRoom.repositories.IParticipantAccountRepository

class ParticipantAccountApplicationService(private val participantAccountRepository: IParticipantAccountRepository) {
    fun getParticipantAccountById(id: String): ParticipantAccountDto {
        return participantAccountRepository.findByIdOrNull(accountId = AccountId(id))
            ?.toDto()
            ?: throw Exception("Participant Account Not Found")
    }

    fun registerParticipantAccount(name: String, iconPath: String): String {
        val participantAccount = ParticipantAccount.create(name, iconPath)
        participantAccountRepository.save(participantAccount)

        return participantAccount.id.value
    }
}
