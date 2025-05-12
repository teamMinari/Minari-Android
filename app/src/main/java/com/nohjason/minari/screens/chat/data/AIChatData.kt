package com.nohjason.minari.screens.chat.data

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