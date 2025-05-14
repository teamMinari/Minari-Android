package com.nohjason.cheongfordo.screens.news

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjason.cheongfordo.network.ApiService
import com.nohjason.cheongfordo.preferences.PreferencesManager
import com.nohjason.cheongfordo.screens.rout.response.GetAllNews
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(
    application: Application,
    private val preferencesManager: PreferencesManager,
    private val apiService: ApiService
) : AndroidViewModel(application) {

    // 전체 뉴스 (카테고리별)
    private val _getAllNews = MutableStateFlow<GetAllNews?>(null)
    val getAllNews: StateFlow<GetAllNews?> = _getAllNews

    fun getAllNews(category: String) {
        viewModelScope.launch {
            val token = preferencesManager.getToken()
            if (token.isNullOrEmpty()) {
                Log.e("NewsViewModel", "getAllNews: 토큰 없음")
                return@launch
            }
            try {
                val response = withContext(Dispatchers.IO) {
                    apiService.getAllNews(token, category)
                }
                if (response.isSuccessful) {
                    _getAllNews.value = response.body()
                    Log.d("NewsViewModel", "getAllNews: $category 뉴스 서버 통신 성공")
                } else {
                    Log.e("NewsViewModel", "getAllNews: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("NewsViewModel", "getAllNews error", e)
            }
        }
    }

    // 핫 뉴스 전용
    private val _hotNews = MutableStateFlow<GetAllNews?>(null)
    val hotNews: StateFlow<GetAllNews?> = _hotNews

    fun getHotNews() {
        viewModelScope.launch {
            val token = preferencesManager.getToken()
            if (token.isNullOrEmpty()) {
                Log.e("NewsViewModel", "getHotNews: 토큰 없음")
                return@launch
            }
            try {
                val response = withContext(Dispatchers.IO) {
                    apiService.getAllNews(token, "HotNews")
                }
                if (response.isSuccessful) {
                    _hotNews.value = response.body()
                    Log.d("NewsViewModel", "getHotNews: 핫뉴스 서버 통신 성공")
                } else {
                    Log.e("NewsViewModel", "getHotNews: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("NewsViewModel", "getHotNews error", e)
            }
        }
    }
}
