package com.chatroom.ddd.domainModels.participantAccount

data class IconPath(
    val value: String
) {
    companion object {
        const val MINIMUM_LENGTH = 1
    }

    init {
        validateField()
    }

    private fun validateField() {
        require(value.length > MINIMUM_LENGTH)
    }
}
