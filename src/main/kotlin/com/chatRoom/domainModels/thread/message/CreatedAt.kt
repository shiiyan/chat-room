package com.chatRoom.domainModels.thread.message

import com.chatRoom.domainCommons.CustomImmutableDateTime
import java.time.LocalDateTime

class CreatedAt(dateTime: LocalDateTime) : CustomImmutableDateTime(dateTime)
