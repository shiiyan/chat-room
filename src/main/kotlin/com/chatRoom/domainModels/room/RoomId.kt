package com.chatRoom.domainModels.room

import com.chatRoom.extensions.noHyphen
import java.util.UUID

data class RoomId(
    val value: String = UUID.randomUUID().toString().noHyphen()
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
