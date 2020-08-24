package com.chatRoom.extensions

fun String.noHyphen() = toLowerCase()
    .replace("-", "")
