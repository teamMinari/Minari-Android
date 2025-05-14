package com.nohjason.myapplication.network

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjason.cheongfordo.network.ApiService
import com.nohjason.myapplication.network.response.Term
import dagger.hilt.android.lifecycle.HiltViewModel
import com.nohjason.cheongfordo.network.response.FinishLearn
import com.nohjason.cheongfordo.network.response.GetAllTermsResponse
import com.nohjason.cheongfordo.preferences.PreferencesManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException


@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiService: ApiService,
    private val preferencesManager: PreferencesManager,
) : ViewModel() {

    private val _allTermsResponse = MutableStateFlow<GetAllTermsResponse?>(null)
    val getAllTerms: StateFlow<GetAllTermsResponse?> = _allTermsResponse

    private val _finishLearn = MutableStateFlow<FinishLearn?>(null)
    val finishLearn: StateFlow<FinishLearn?> = _finishLearn

    // 전체 용어 목록 조회 (페이징 포함)
    fun getAllTerms(page: Int, size: Int) {
        viewModelScope.launch {
            val token = preferencesManager.getToken()
            if (token.isNullOrEmpty()) {
                Log.e("MainViewModel", "getAllTerms: 토큰 없음")
                return@launch
            }
            try {
                val response = withContext(Dispatchers.IO) {
                    apiService.getAlTerms(token, page, size)
                }
                if (response.isSuccessful) {
                    _allTermsResponse.value = response.body()
                    Log.d("MainViewModel", "getAllTerms: 전체 용어 서버 통신 성공")
                } else {
                    Log.e("MainViewModel", "getAllTerms: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: IOException) {
                Log.e("MainViewModel", "getAllTerms: 네트워크 오류", e)
            } catch (e: HttpException) {
                Log.e("MainViewModel", "getAllTerms: HTTP 오류 - 코드: ${e.code()}", e)
            } catch (e: Exception) {
                Log.e("MainViewModel", "getAllTerms: 알 수 없는 오류", e)
            }
        }
    }

    // 학습 완료 처리
    fun finishLearn(category: String, id: Int) {
        viewModelScope.launch {
            val token = preferencesManager.getToken()
            if (token.isNullOrEmpty()) {
                Log.e("MainViewModel", "finishLearn: 토큰 없음")
                return@launch
            }
            try {
                val response = withContext(Dispatchers.IO) {
                    apiService.finishLearn(token, category, id)
                }
                if (response.isSuccessful) {
                    _finishLearn.value = response.body()
                    Log.d("MainViewModel", "finishLearn: 학습 완료 서버 통신 성공")
                } else {
                    Log.e("MainViewModel", "finishLearn: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: IOException) {
                Log.e("MainViewModel", "finishLearn: 네트워크 오류", e)
            } catch (e: HttpException) {
                Log.e("MainViewModel", "finishLearn: HTTP 오류 - 코드: ${e.code()}", e)
            } catch (e: Exception) {
                Log.e("MainViewModel", "finishLearn: 알 수 없는 오류", e)
            }
        }
    }
}

