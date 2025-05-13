package com.nohjason.cheongfordo.screens.chat.data

data class ChatResponse(
    val choices: List<Choice>
)

data class Choice(
    val message: Message
)

data class Message(
    val role: String,
    val content: String
)

data class ChatMessage(
    val question: String
)

// UI에서 쓸 메시지 모델 (isUser 구분)
data class ChatMessageUi(
    val text: String,
    val isUser: Boolean
)