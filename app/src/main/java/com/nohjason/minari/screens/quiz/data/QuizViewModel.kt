package com.nohjason.minari.screens.quiz.data

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.nohjason.myapplication.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import androidx.lifecycle.viewModelScope
import com.nohjason.minari.network.ApiService
import com.nohjason.minari.preferences.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager,
    private val api: ApiService
) : ViewModel() {

    private val _playData = MutableStateFlow<PlayData?>(null)
    val playData: StateFlow<PlayData?> = _playData

    private val _uiState = MutableStateFlow(QuizUiState.Waiting)
    val uiState: StateFlow<QuizUiState> = _uiState

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    // 퀴즈 문제 불러오기
    fun loadQuestions(level: Int) {
        viewModelScope.launch {
            _loading.value = true
            val token = preferencesManager.getToken() ?: return@launch
            try {
                val response = withContext(Dispatchers.IO) {
                    api.getQuestion(token = token, level = level)
                }
                // 성공 시 PlayData로 변환
                if (response.status == 200 && response.data.isNotEmpty()) {
                    _playData.value = PlayData(
                        userCurrent = 0,
                        point = 0,
                        qtNum = 0,
                        qtList = response.data
                    )
                    _uiState.value = QuizUiState.Waiting
                }
            } catch (e: Exception) {
                // 에러 처리(로딩 중지)
            } finally {
                _loading.value = false
            }
        }
    }

    // 정답 제출
    fun submitAnswer(userAnswer: Boolean) {
        val data = _playData.value ?: return
        val currentQuestion = data.qtList.getOrNull(data.qtNum) ?: return
        val isCorrect = userAnswer == currentQuestion.qtAnswer

        // 상태 업데이트
        _uiState.value = if (isCorrect) QuizUiState.Correct else QuizUiState.Wrong

        // 점수/정답수 업데이트
        if (isCorrect) {
            _playData.value = data.copy(
                point = data.point + 1,
                userCurrent = data.userCurrent + 1
            )
        }
    }

    fun showTip() {
        _uiState.value = QuizUiState.Tip
    }

    // 다음 문제로 이동
    fun nextQuestion() {
        val data = _playData.value ?: return
        val nextNum = data.qtNum + 1
        if (nextNum < data.qtList.size) {
            _playData.value = data.copy(qtNum = nextNum)
            _uiState.value = QuizUiState.Waiting
        }
        // 마지막 문제면 결과 화면 이동은 UI에서 처리
    }

    // 상태 초기화 (퀴즈 다시 시작 등)
    fun reset() {
        _playData.value?.let { data ->
            _playData.value = data.copy(qtNum = 0, userCurrent = 0, point = 0)
            _uiState.value = QuizUiState.Waiting
        }
    }
}