package com.nohjason.cheongfordo.screens.profile.profile_data

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjason.cheongfordo.network.ApiService
import com.nohjason.cheongfordo.preferences.PreferencesManager
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

    // PreferencesManager는 생성자 주입 대신 내부에서 생성 (필요시 DI로 변경 가능)
    private val preferencesManager = PreferencesManager(application)

    // 프로필 데이터
    private val _profileData = MutableStateFlow<ProfileResponse?>(null)
    val profileData: StateFlow<ProfileResponse?> = _profileData

    fun getProfile() {
        viewModelScope.launch {
            val token = preferencesManager.getToken()
            if (token.isNullOrEmpty()) {
                Log.e("ProfileViewModel", "getProfile: 토큰 없음")
                return@launch
            }
            try {
                val response = withContext(Dispatchers.IO) {
                    api.getProfile(token)
                }
                if (response.isSuccessful) {
                    _profileData.value = response.body()
                    Log.d("ProfileViewModel", "getProfile: 프로필 서버 통신 성공")
                } else {
                    Log.e("ProfileViewModel", "getProfile: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("ProfileViewModel", "getProfile: 오류 발생", e)
            }
        }
    }

    // 로그아웃 데이터
    private val _logoutData = MutableStateFlow<LogOutResponse?>(null)
    val logoutData: StateFlow<LogOutResponse?> = _logoutData

    fun getLogout() {
        viewModelScope.launch {
            val token = preferencesManager.getToken()
            if (token.isNullOrEmpty()) {
                Log.e("ProfileViewModel", "getLogout: 토큰 없음")
                return@launch
            }
            try {
                val response = withContext(Dispatchers.IO) {
                    api.getLogout(token)
                }
                if (response.isSuccessful) {
                    _logoutData.value = response.body()
                    Log.d("ProfileViewModel", "getLogout: 로그아웃 서버 통신 성공")
                } else {
                    Log.e("ProfileViewModel", "getLogout: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("ProfileViewModel", "getLogout: 오류 발생", e)
            }
        }
    }
}
