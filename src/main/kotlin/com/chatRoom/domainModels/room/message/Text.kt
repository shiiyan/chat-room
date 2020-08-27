package com.chatRoom.domainModels.room.message

data class Text(
    var value: String
) {
    companion object {
        const val MINIMUM_LENGTH = 1
        const val MAXIMUM_LENGTH = 1000
    }

    init {
        validateField()
    }

    private fun validateField() {
        require(value.length in MINIMUM_LENGTH..MAXIMUM_LENGTH)
    }

    fun updateValue(newValue: String) {
        value = newValue
        validateField() // dig into this method to make more sense
    }
}
