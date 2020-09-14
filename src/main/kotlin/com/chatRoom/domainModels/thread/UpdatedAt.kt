package com.chatRoom.domainModels.thread

import com.chatRoom.domainCommons.CustomImmutableDateTime
import java.time.LocalDateTime

class UpdatedAt(dateTime: LocalDateTime) : CustomImmutableDateTime(dateTime)
