// package com.chatroom.ddd.domainModels.message
//
// import com.chatroom.ddd.application.domainModels.participantAccount.AccountId
// import com.chatroom.ddd.application.domainModels.room.RoomId
// import com.chatroom.ddd.application.intraAggregateDataClasses.IntraAggregateMessageId
// import com.chatroom.ddd.application.repositories.message.IMessageRepository
// import com.chatroom.ddd.application.repositories.room.IRoomRepository
//
// class MessageDomainService(
//    private val messageRepository: IMessageRepository,
//    private val roomRepository: IRoomRepository
// ) {
//    fun getMessageByRoomIdForParticipant(roomId: String): List<MessageDto> {
//        // TODO: getCurrentAccountId from session yet to implemented
//        val currentAccountId = "account-id-not-exist"
//        val countOfMessages = messageRepository.findCountByAccountId(AccountId(currentAccountId))
//
//        val room = roomRepository.findByIdOrNull(RoomId(roomId))!!
//
//        if (countOfMessages < room.toDto().level) {
//            throw Exception("Room Not Accessible")
//        }
//
//        val foundMessages = messageRepository.findAllByRoomId(RoomId(roomId))
//
//        return foundMessages.map { it.toDto() }
//    }
//
//    fun getMessageByRoomIdForRoomCreator(roomId: String): List<MessageDto> {
//        val foundMessages = messageRepository.findAllByRoomId(RoomId(roomId))
//
//        return foundMessages.map { it.toDto() }
//    }
//
//    fun sendMessageByParticipant(text: String, imagePaths: List<String>, roomId: String): String {
//        // TODO: getCurrentAccountId from session yet to implemented
//        val currentAccountId = "account-id-not-exist"
//        val countOfMessages = messageRepository.findCountByAccountId(AccountId(currentAccountId))
//
//        val foundRoom = roomRepository.findByIdOrNull(RoomId(roomId))!!
//
//        if (countOfMessages < foundRoom.toDto().level) {
//            throw Exception("Room Not Accessible")
//        }
//
//        val newMessage = Message.create(text, imagePaths, currentAccountId, roomId)
//        messageRepository.save(newMessage)
//
//        foundRoom.updateLatestMessageList(
//            listOf(IntraAggregateMessageId(newMessage.toDto().id))
//        )
//        roomRepository.save(foundRoom)
//
//        return newMessage.toDto().id
//    }
//
//    fun sendMessageByRoomCreator(text: String, imagePaths: List<String>, roomId: String): String {
//        // TODO: getCurrentAccountId from session yet to implemented
//        val currentAccountId = "account-id-not-exist"
//        val foundRoom = roomRepository.findByIdOrNull(RoomId(roomId))!!
//
//        val newMessage = Message.create(text, imagePaths, currentAccountId, roomId)
//        messageRepository.save(newMessage)
//
//        foundRoom.updateLatestMessageList(
//            listOf(IntraAggregateMessageId(newMessage.toDto().id))
//        )
//        roomRepository.save(foundRoom)
//
//        return newMessage.toDto().id
//    }
//
//    fun editMessage(messageId: String, newText: String, newImagePaths: List<String>) {
//        // TODO: getCurrentAccountId from session yet to implemented
//        val currentAccountId = "account-id-not-exist"
//        val foundMessage = messageRepository.findByIdOrNull(MessageId(messageId))!!
//
//        if (!foundMessage.isCreatorOfMessage(currentAccountId)) {
//            throw Exception("No Right To Edit")
//        }
//
//        foundMessage.updateText(newText)
//        foundMessage.updateImage(newImagePaths)
//    }
// }
