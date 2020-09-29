package com.chatroom.app.dddImplement.domain.room

class Level(var value: Int) {
    companion object {
        const val MINIMUM_VALUE = 0
        const val MAXIMUM_VALUE = 100
    }

    init {
        validateField()
    }

    private fun validateField() {
        require(value in MINIMUM_VALUE..MAXIMUM_VALUE)
    }

    fun lowerBy(number: Int) {
        if (number < 0) {
            throw Exception("Negative Number Forbidden")
        }

        value -= number
        validateField()
    }

    fun isHigherThan(number: Int) = value >= number
}
