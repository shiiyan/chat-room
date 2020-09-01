package com.chatRoom.domainModels.room

import com.chatRoom.domainCommons.CustomImmutableDateTime
import java.time.LocalDateTime

class CreatedAt(dateTime: LocalDateTime) : CustomImmutableDateTime(dateTime)
