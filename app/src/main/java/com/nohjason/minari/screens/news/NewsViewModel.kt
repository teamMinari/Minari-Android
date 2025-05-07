package com.nohjason.minari.screens.news

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjason.minari.preferences.PreferencesManager
import com.nohjason.minari.screens.rout.response.GetAllNews
import com.nohjason.myapplication.network.RetrofitInstance.api
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    private val preferencesManager = PreferencesManager(application)

    private val _getAllNews = MutableStateFlow<GetAllNews?>(null)
    val getAllNews: StateFlow<GetAllNews?> = _getAllNews

    fun getAllNews(category: String) {
        viewModelScope.launch {
            val token = preferencesManager.getToken()
            if (token.isNullOrEmpty()) {
                Log.e("TAG", "getAllNews: 토큰 없음")
                return@launch
            }
            try {
                val response = withContext(Dispatchers.IO) {
                    Log.d("TAG", "getAllNews: $token")
                    api.getAllNews(token, category)
                }
                if (response.isSuccessful) {
                    _getAllNews.value = response.body()
                    Log.d("TAG", "getAllNews: 모든 뉴스 서버 통신 성공")
                } else {
                    Log.e("TAG", "getAllNews: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: IOException) {
                Log.e("TAG", "getAllNews: 네트워크 오류", e)
            } catch (e: HttpException) {
                Log.e("TAG", "getAllNews: HTTP 오류 - 코드: ${e.code()}", e)
            } catch (e: Exception) {
                Log.e("TAG", "getAllNews: 알 수 없는 오류", e)
            }
        }
    }
}
