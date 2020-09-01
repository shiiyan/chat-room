package com.chatRoom.domainModels.message

class Image(
    var pathList: List<String>
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

    fun updatePathList(newPathList: List<String>) {
        pathList = newPathList
        validateField()
    }
}
