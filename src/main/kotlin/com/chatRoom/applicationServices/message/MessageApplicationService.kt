package com.chatRoom.applicationServices.message

import com.chatRoom.domainModels.message.MessageDomainService
import com.chatRoom.repositories.message.IMessageRepository
import com.chatRoom.repositories.room.IRoomRepository

class MessageApplicationService(
    private val messageRepository: IMessageRepository,
    private val roomRepository: IRoomRepository
) {
    fun getMessageByRoomId() {
        // 参加者は「ルームレベル」の値にメッセージ送信数が満たない参加者はその「ルーム」を閲覧することができず、
        // （ただし、その「ルーム」の作成者のみ例外で「ルームレベル」に関係なく閲覧・送信ができる。）
//        TODO("Check whether level is enough")
//        TODO("Not yet implemented")
    }

//    fun getMessageByAccountId() {
//        TODO("Not yet implemented")
//    }

    fun sendMessage(text: String, imagePaths: List<String>, roomId: String): String {
//        TODO("Check whether level is enough")
//        TODO("")
        // 「ルームレベル」の値にメッセージ送信数が満たない参加者「ルーム」に「メッセージ」を送信できない。
        // （ただし、その「ルーム」の作成者のみ例外で「ルームレベル」に関係なく閲覧・送信ができる。）

        return MessageDomainService(messageRepository, roomRepository)
            .sendMessage(text, imagePaths, roomId)
    }

    fun editMessage(messageId: String, newText: String, newImagePaths: List<String>) {
        MessageDomainService(messageRepository, roomRepository)
            .editMessage(messageId, newText, newImagePaths)
    }
}
