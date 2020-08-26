package com.chatRoom.domainModels.room

data class Name(
    val value: String
) {
    companion object {
        const val MINIMUM_LENGTH = 1
        const val MAXIMUM_LENGTH = 16
    }

    init {
        validateField()
    }

    private fun validateField() {
        require(value.length in MINIMUM_LENGTH..MAXIMUM_LENGTH)
    }
}
