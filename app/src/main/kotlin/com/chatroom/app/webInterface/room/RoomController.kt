package com.chatroom.app.webInterface.room

import com.chatroom.app.dddImplement.application.room.RoomApplicationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RoomController(
    @Autowired private val roomApplicationService: RoomApplicationService
) {
    @PostMapping("/create_room")
    fun createRoom(
        @CookieValue(name = "account-id") accountId: String,
        @RequestBody createRoomRequestBody: CreateRoomRequestBody
    ): String = roomApplicationService.createRoom(
        name = createRoomRequestBody.name,
        level = createRoomRequestBody.level,
        currentAccountId = accountId
    )
}
