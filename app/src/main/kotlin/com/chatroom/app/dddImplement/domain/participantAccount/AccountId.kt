package com.chatroom.app.dddImplement.domain.participantAccount

data class AccountId(
    val value: String
) {
    companion object {
        const val MINIMUM_LENGTH = 1
    }

    init {
        validateField()
    }

    private fun validateField() {
        require(value.length >= MINIMUM_LENGTH)
        require("[a-z\\d-]+".toRegex().matches(value))
    }
}
