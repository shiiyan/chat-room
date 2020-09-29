package com.chatroom.ddd.domainModels.message

data class MessageId(
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
