package com.nohjason.minari.screens.chat.data

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.nohjason.minari.network.ApiService
import com.nohjason.minari.preferences.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager,
    private val apiService: ApiService
) : ViewModel() {

    // 채팅 메시지 리스트
    private val _chatList = MutableStateFlow<List<ChatMessageUi>>(emptyList())
    val chatList: StateFlow<List<ChatMessageUi>> = _chatList

    // 로딩 상태
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun sendMessage(question: String) {
        // 사용자 메시지 추가
        _chatList.value = _chatList.value + ChatMessageUi(question, isUser = true)
        _isLoading.value = true

        viewModelScope.launch {
            try {
                val token = preferencesManager.getToken()
                if (token.isNullOrEmpty()) {
                    Log.e("ChatViewModel", "토큰이 없습니다.")
                    _isLoading.value = false
                    return@launch
                }
                // 실제 서버에 POST /chat 요청
                val response = apiService.chat(
                    token = token.toString(),  // "Bearer " 접두사 추가
                    body = ChatMessage(question)
                )
                if (response.isSuccessful) {
                    val body = response.body()
                    val aiContent = body?.choices?.firstOrNull()?.message?.content
                        ?: "AI 답변을 불러오지 못했어요."
                    // AI 메시지 추가
                    _chatList.value = _chatList.value + ChatMessageUi(aiContent, isUser = false)
                } else {
                    Log.e("ChatViewModel", "서버 응답 실패: 코드=${response.code()}, 메시지=${response.message()}")
                    _chatList.value = _chatList.value + ChatMessageUi("AI 답변을 불러오지 못했어요.", isUser = false)
                }
            } catch (e: Exception) {
                Log.e("ChatViewModel", "네트워크 요청 중 오류 발생", e)
                _chatList.value = _chatList.value + ChatMessageUi("네트워크 오류가 발생했어요.", isUser = false)
            } finally {
                _isLoading.value = false
            }
        }
    }
}

