package com.nohjason.cheongfordo.screens.quiz.data

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import kotlin.random.Random
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager,
    private val apiService: ApiService
) : ViewModel() {

    private val _questionData = MutableStateFlow<QuestionResponse?>(null)
    val questionData: StateFlow<QuestionResponse?> = _questionData

    private val _pointData = MutableStateFlow<PointResponse?>(null)
    val pointData: StateFlow<PointResponse?> = _pointData

    private val _playData = MutableStateFlow<PlayData?>(null)
    val playData: StateFlow<PlayData?> = _playData

    /**
     * suspend 함수로 퀴즈 질문 가져오기
     */

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

    /**
     * 점수 서버 전송 함수 (과거 postPoint)
     */
    fun postPoint(point: PointRequest) {
        viewModelScope.launch {
            val token = preferencesManager.getToken()
            if (token.isNullOrEmpty()) {
                Log.e("QuizViewModel", "postPoint: 토큰 없음")
                return@launch
            }
            try {
                val response = withContext(Dispatchers.IO) {
                    apiService.postPoint(token, point)
                }
                if (response.isSuccessful) {
                    _pointData.value = response.body()
                    Log.d("QuizViewModel", "postPoint: 포인트 서버 통신 성공")
                } else {
                    Log.e("QuizViewModel", "postPoint: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("QuizViewModel", "postPoint: 오류 발생", e)
            }
        }
    }

    // PlayData 초기화
    fun initializePlayData(data: PlayData) {
        _playData.value = data
    }

    // 다음 문제로 이동
    fun nextQuestion() {
        _playData.value?.let { data ->
            val newQtNum = data.qtNum + 1
            if (newQtNum < data.qtList.size) {
                _playData.value = data.copy(qtNum = newQtNum)
            }
        }
    }

    // 점수 업데이트
    fun updatePoints(newPoints: Int) {
        _playData.value?.let { data ->
            _playData.value = data.copy(point = newPoints)
        }
    }

    // 점수 차감 (과거 minusPoints 기능)
    fun minusPoints() {
        _playData.value?.let { data ->
            val minusPoint = when (data.qtLevel) {
                1 -> 10
                2 -> 20
                else -> 40
            }
            val updatedPoint = (data.point - minusPoint).coerceAtLeast(0)
            updatePoints(updatedPoint)
        }
    }

    // 현재 진행도 업데이트
    fun updateCurrent(newCurrent: Int) {
        _playData.value?.let { data ->
            _playData.value = data.copy(userCurrent = newCurrent)
        }
    }

    // 정답 제출 처리 (과거 submitAnswer 기능)
    fun submitAnswer(userAnswer: Boolean, correctAnswer: Boolean) {
        _playData.value?.let { data ->
            val addPoint = when (data.qtLevel) {
                1 -> Random.nextInt(30, 41)
                2 -> Random.nextInt(41, 81)
                else -> Random.nextInt(81, 101)
            }
            if (userAnswer == correctAnswer) {
                updatePoints(data.point + addPoint)
                updateCurrent(data.userCurrent + 1)
            }
        }
    }
}
