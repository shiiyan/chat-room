package com.chatRoom.repositories

import com.chatRoom.domainModels.participantAccount.AccountId
import com.chatRoom.domainModels.participantAccount.ParticipantAccount

interface IParticipantAccountRepository {
    abstract fun findByIdOrNull(accountId: AccountId): ParticipantAccount?
    abstract fun save(participantAccount: ParticipantAccount)
}
