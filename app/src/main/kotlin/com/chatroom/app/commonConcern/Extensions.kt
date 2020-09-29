package com.chatroom.app.commonConcern

fun String.noHyphen() = toLowerCase()
    .replace("-", "")
