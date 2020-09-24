package com.chatRoom.domainModels.messageInThread

import com.chatRoom.domainCommons.CustomImmutableDateTime
import java.time.LocalDateTime

class CreatedAt(dateTime: LocalDateTime) : CustomImmutableDateTime(dateTime)
