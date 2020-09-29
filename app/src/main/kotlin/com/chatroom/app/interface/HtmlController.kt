package com.chatroom.app.`interface`

import com.chatroom.app.commonConcern.noHyphen
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.GetMapping
import java.util.UUID
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@Controller
class HtmlController {
    @GetMapping("/")
    fun readCookie(
        @CookieValue(name = "account-id") accountId: String
    ): ResponseEntity<String> = ResponseEntity(
        "The following cookie are read: $accountId",
        HttpStatus.OK
    )

    @GetMapping("/login")
    fun setCookie(response: HttpServletResponse): ResponseEntity<String> {
        val newCookie = Cookie(
            "account-id",
            generateNewAccountId()
        )
//        newCookie.secure = true
        newCookie.isHttpOnly = true
        response.addCookie(newCookie)

        return ResponseEntity(
            "New cookie are sent: ${newCookie.value}",
            HttpStatus.OK
        )
    }

    private fun generateNewAccountId() = UUID.randomUUID().toString().noHyphen()
}
