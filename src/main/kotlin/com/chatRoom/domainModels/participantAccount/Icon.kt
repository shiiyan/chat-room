package com.chatRoom.domainModels.participantAccount

data class Icon(
    val path: String
) {
    companion object {
        const val MINIMUM_LENGTH = 1
    }

    init {
        validateField()
    }

    private fun validateField() {
        require(path.length > MINIMUM_LENGTH)
    }
}
