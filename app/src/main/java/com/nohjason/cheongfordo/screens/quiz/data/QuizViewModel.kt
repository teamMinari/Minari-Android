package com.nohjason.cheongfordo.screens.quiz.data

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import androidx.lifecycle.viewModelScope
import com.nohjason.cheongfordo.network.ApiService
import com.nohjason.cheongfordo.preferences.PreferencesManager
import com.nohjason.cheongfordo.screens.quiz.quiz_main.selectPlayData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager,
    private val apiService: ApiService
) : ViewModel() {

    private val _playData = MutableStateFlow<PlayData?>(null)
    val playData: StateFlow<PlayData?> = _playData

    /**
     * 서버에서 퀴즈 리스트 받아오기 (suspend 함수)
     */
    suspend fun fetchQuestions(): QuestionResponse {
        val token = preferencesManager.getToken()
        if (token.isNullOrEmpty()) {
            throw IllegalStateException("토큰이 없습니다.")
        }

        return withContext(Dispatchers.IO) {
            apiService.getQuestion(token)
        }
    }

    /**
     * 서버에서 퀴즈 받아와 playData 초기화 (비동기 호출용)
     */
    fun loadQuestions(
        onSuccess: () -> Unit = {},
        onError: (String) -> Unit = {}
    ) {
        viewModelScope.launch {
            try {
                val response = fetchQuestions()
                if (response.status == 0 && response.data.isNotEmpty()) {
                    val dataList = selectPlayData(qestionAll = response) // 가공 함수 호출
                    initializePlayData(dataList)
                    onSuccess()
                } else {
                    onError(response.message)
                }
            } catch (e: Exception) {
                onError(e.message ?: "알 수 없는 오류가 발생했습니다.")
            }
        }
    }

    // PlayData 초기화 함수
    fun initializePlayData(data: PlayData) {
        _playData.value = data
    }

    // 퀴즈를 다음으로 진행하는 함수
    fun nextQuestion() {
        _playData.value?.let { data ->
            val newQtNum = data.qtNum + 1
            if (newQtNum < data.qtList.size) {
                _playData.value = data.copy(qtNum = newQtNum)
            }
        }
    }

    // 점수 업데이트 함수
    fun updatePoints(newPoints: Int) {
        _playData.value?.let { data ->
            _playData.value = data.copy(point = newPoints)
        }
    }

    fun updateCurrent(newCurrent: Int) {
        _playData.value?.let { data ->
            _playData.value = data.copy(userCurrent = newCurrent)
        }
    }

    // 정답 제출 함수
    fun submitAnswer(userAnswer: Boolean, correctAnswer: Boolean) {
        _playData.value?.let { data ->
            if (userAnswer == correctAnswer) {
                updatePoints(data.point + 1)
                updateCurrent(data.userCurrent + 1)
            }
        }
    }
}


