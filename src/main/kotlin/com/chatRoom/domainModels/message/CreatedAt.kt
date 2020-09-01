package com.chatRoom.domainModels.message

import com.chatRoom.domainCommons.CustomImmutableDateTime
import java.time.LocalDateTime

class CreatedAt(dateTime: LocalDateTime) : CustomImmutableDateTime(dateTime)
