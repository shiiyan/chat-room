package com.chatRoom.domainModels.thread.message

import com.chatRoom.domainCommons.CustomMutableDateTime
import java.time.LocalDateTime

class UpdatedAt(dateTime: LocalDateTime) : CustomMutableDateTime(dateTime)
