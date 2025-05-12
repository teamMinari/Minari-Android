package com.nohjason.minari.screens.profile.profile_data

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjason.minari.network.ApiService
import com.nohjason.minari.preferences.PreferencesManager
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
class ProfileViewModel @Inject constructor(
    application: Application,
    private val api: ApiService
) : AndroidViewModel(application) {

    private val preferencesManager = PreferencesManager(application)

    private val _profileData = MutableStateFlow<ProfileResponse?>(null)
    val profileData: StateFlow<ProfileResponse?> = _profileData

    fun getProfile() {
        viewModelScope.launch {
            val token = preferencesManager.getToken()
            if (token.isNullOrEmpty()) {
                Log.e("TAG", "getProfile: 토큰 없음")
                return@launch
            }
            try {
                val response = withContext(Dispatchers.IO) {
                    api.getProfile(token)
                }
                if (response.isSuccessful) {
                    _profileData.value = response.body()
                    Log.d("TAG", "getProfile: 전체 포도송이 서버 통신 성공")
                } else {
                    Log.e("TAG", "getProfile: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: IOException) {
                Log.e("TAG", "getProfile: 네트워크 오류", e)
            } catch (e: HttpException) {
                Log.e("TAG", "getProfile: HTTP 오류 - 코드: ${e.code()}", e)
            } catch (e: Exception) {
                Log.e("TAG", "getProfile: 알 수 없는 오류", e)
            }
        }
    }
}



