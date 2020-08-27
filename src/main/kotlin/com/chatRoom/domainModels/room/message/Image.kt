package com.chatRoom.domainModels.room.message

data class Image(
    val pathList: List<String>
) {
    companion object {
        const val MINIMUM_LENGTH = 1
    }

    init {
        validateField()
    }

    private fun validateField() {
        require(pathList.all { it.length > MINIMUM_LENGTH })
    }
}
