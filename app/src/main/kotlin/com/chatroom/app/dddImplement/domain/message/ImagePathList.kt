package com.chatroom.app.dddImplement.domain.message

class ImagePathList(
    var value: List<String>
) {
    companion object {
        const val MINIMUM_LENGTH = 1
    }

    init {
        validateField()
    }

    private fun validateField() {
        require(value.all { it.length > MINIMUM_LENGTH })
    }

    fun updatePathList(newPathList: List<String>) {
        value = newPathList
        validateField()
    }
}
