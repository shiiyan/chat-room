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

    fun downBy(target: Int) {
        value -= target
    }

    fun isQualified(target: Int) = value >= target
}
