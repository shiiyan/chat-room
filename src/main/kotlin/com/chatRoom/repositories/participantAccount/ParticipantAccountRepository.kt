package com.chatRoom.repositories.participantAccount

import com.chatRoom.domainModels.participantAccount.AccountId
import com.chatRoom.domainModels.participantAccount.ParticipantAccount

class ParticipantAccountRepository : IParticipantAccountRepository {
    override fun findAll(): List<ParticipantAccount> {
        TODO("Not yet implemented")
    }

    override fun findByIdOrNull(accountId: AccountId): ParticipantAccount? {
        TODO("Not yet implemented")
    }

    override fun save(participantAccount: ParticipantAccount) {
        TODO("Not yet implemented")
    }
}
