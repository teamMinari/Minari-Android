package com.nohjason.cheongfordo.screens.news

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
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

    private val _hotNews = MutableStateFlow<GetAllNews?>(null)
    val hotNews: StateFlow<GetAllNews?> = _hotNews

    private val _categoryNews = MutableStateFlow<GetAllNews?>(null)
    val categoryNews: StateFlow<GetAllNews?> = _categoryNews

    fun getHotNews() {
        viewModelScope.launch {
            val token = preferencesManager.getToken()
            if (token.isNullOrEmpty()) return@launch
            try {
                val response = withContext(Dispatchers.IO) {
                    apiService.getAllNews(token, "HotNews")
                }
                if (response.isSuccessful) {
                    _hotNews.value = response.body()
                }
            } catch (e: Exception) {
                Log.e("NewsViewModel", "getHotNews error", e)
            }
        }
    }

    fun getCategoryNews(category: String) {
        viewModelScope.launch {
            val token = preferencesManager.getToken()
            if (token.isNullOrEmpty()) return@launch
            try {
                val response = withContext(Dispatchers.IO) {
                    apiService.getAllNews(token, category)
                }
                if (response.isSuccessful) {
                    _categoryNews.value = response.body()
                }
            } catch (e: Exception) {
                Log.e("NewsViewModel", "getCategoryNews error", e)
            }
        }
    }
}

