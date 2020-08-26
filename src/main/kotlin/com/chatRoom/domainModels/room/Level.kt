package com.chatRoom.domainModels.room

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

    fun lower(lowerBy: Int) {
        if (lowerBy <= 0) {
            throw Exception("Non Positive Number Forbidden")
        }

        value -= lowerBy
    }

    fun isQualified(target: Int) = value >= target
}
