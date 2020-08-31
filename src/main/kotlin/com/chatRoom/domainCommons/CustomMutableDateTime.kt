package com.chatRoom.domainCommons

import java.time.LocalDateTime

open class CustomMutableDateTime(var dateTime: LocalDateTime) {
    fun changeDateTime(newDateTime: LocalDateTime) {
        dateTime = newDateTime
    }
}
