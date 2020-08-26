package com.chatRoom.repositories.participantAccount

import com.chatRoom.domainModels.participantAccount.AccountId
import com.chatRoom.domainModels.participantAccount.ParticipantAccount

class InMemoryParticipantAccountRepository() : IParticipantAccountRepository {
    private val data: MutableMap<AccountId, ParticipantAccount> = mutableMapOf<AccountId, ParticipantAccount>()

    override fun findAll() = data.values.map { it }

    override fun findByIdOrNull(accountId: AccountId) = data[accountId]

    override fun save(participantAccount: ParticipantAccount) {
        data[participantAccount.id] = participantAccount
    }
}
