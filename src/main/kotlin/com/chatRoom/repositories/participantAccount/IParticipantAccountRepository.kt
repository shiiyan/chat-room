package com.chatRoom.repositories.participantAccount

import com.chatRoom.domainModels.participantAccount.AccountId
import com.chatRoom.domainModels.participantAccount.ParticipantAccount

interface IParticipantAccountRepository {
    abstract fun findAll(): List<ParticipantAccount>
    abstract fun findByIdOrNull(accountId: AccountId): ParticipantAccount?
    abstract fun save(participantAccount: ParticipantAccount)
}
