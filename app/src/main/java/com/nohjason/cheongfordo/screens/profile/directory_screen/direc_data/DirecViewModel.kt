package com.nohjason.cheongfordo.screens.profile.directory_screen.direc_data

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
class DirecViewModel @Inject constructor(
    application: Application,
    private val preferencesManager: PreferencesManager,
    private val apiService: ApiService
) : AndroidViewModel(application) {

    // 용어 데이터
    private val _direcTermData = MutableStateFlow<DirecTermResponse?>(null)
    val direcTermData: StateFlow<DirecTermResponse?> = _direcTermData

    // 포도씨 데이터
    private val _direcGpseData = MutableStateFlow<DirecGpseResponse?>(null)
    val direcGpseData: StateFlow<DirecGpseResponse?> = _direcGpseData

    // 포도알 데이터
    private val _direcGpsData = MutableStateFlow<DirecGpsResponse?>(null)
    val direcGpsData: StateFlow<DirecGpsResponse?> = _direcGpsData

    // 포도송이 데이터
    private val _direcGpData = MutableStateFlow<DirecGpResponse?>(null)
    val direcGpData: StateFlow<DirecGpResponse?> = _direcGpData

    // 용어 조회
    fun getDirecTerm() {
        viewModelScope.launch {
            val token = preferencesManager.getToken()
            if (token.isNullOrEmpty()) {
                Log.e("TAG", "getDirecTerm: 토큰 없음")
                return@launch
            }

            try {
                val response = withContext(Dispatchers.IO) {
                    apiService.getDiercTerm(token)
                }
                if (response.isSuccessful) {
                    _direcTermData.value = response.body()
                    Log.d("TAG", "getDirecTerm: 저장목록 용어 서버 통신 성공")
                } else {
                    Log.e("TAG", "getDirecTerm: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: IOException) {
                Log.e("TAG", "getDirecTerm: 네트워크 오류", e)
            } catch (e: HttpException) {
                Log.e("TAG", "getDirecTerm: HTTP 오류 - 코드: ${e.code()}", e)
            } catch (e: Exception) {
                Log.e("TAG", "getDirecTerm: 알 수 없는 오류", e)
            }
        }
    }

    // 포도씨 조회
    fun getDirecGpse() {
        viewModelScope.launch {
            val token = preferencesManager.getToken()
            if (token.isNullOrEmpty()) {
                Log.e("TAG", "getDirecGpse: 토큰 없음")
                return@launch
            }

            try {
                val response = withContext(Dispatchers.IO) {
                    apiService.getDiercGpse(token)
                }
                if (response.isSuccessful) {
                    _direcGpseData.value = response.body()
                    Log.d("TAG", "getDirecGpse: 저장목록 포도씨 서버 통신 성공")
                } else {
                    Log.e("TAG", "getDirecGpse: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: IOException) {
                Log.e("TAG", "getDirecGpse: 네트워크 오류", e)
            } catch (e: HttpException) {
                Log.e("TAG", "getDirecGpse: HTTP 오류 - 코드: ${e.code()}", e)
            } catch (e: Exception) {
                Log.e("TAG", "getDirecGpse: 알 수 없는 오류", e)
            }
        }
    }

    // 포도알 조회
    fun getDirecGps() {
        viewModelScope.launch {
            val token = preferencesManager.getToken()
            if (token.isNullOrEmpty()) {
                Log.e("TAG", "getDirecGps: 토큰 없음")
                return@launch
            }

            try {
                val response = withContext(Dispatchers.IO) {
                    apiService.getDiercGps(token)
                }
                if (response.isSuccessful) {
                    _direcGpsData.value = response.body()
                    Log.d("TAG", "getDirecGps: 저장목록 포도알 서버 통신 성공")
                } else {
                    Log.e("TAG", "getDirecGps: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: IOException) {
                Log.e("TAG", "getDirecGps: 네트워크 오류", e)
            } catch (e: HttpException) {
                Log.e("TAG", "getDirecGps: HTTP 오류 - 코드: ${e.code()}", e)
            } catch (e: Exception) {
                Log.e("TAG", "getDirecGps: 알 수 없는 오류", e)
            }
        }
    }

    // 포도송이 조회
    fun getDirecGp() {
        viewModelScope.launch {
            val token = preferencesManager.getToken()
            if (token.isNullOrEmpty()) {
                Log.e("TAG", "getDirecGp: 토큰 없음")
                return@launch
            }

            try {
                val response = withContext(Dispatchers.IO) {
                    apiService.getDierctGp(token)
                }
                if (response.isSuccessful) {
                    _direcGpData.value = response.body()
                    Log.d("TAG", "getDirecGp: 저장목록 포도송이 서버 통신 성공")
                } else {
                    Log.e("TAG", "getDirecGp: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: IOException) {
                Log.e("TAG", "getDirecGp: 네트워크 오류", e)
            } catch (e: HttpException) {
                Log.e("TAG", "getDirecGp: HTTP 오류 - 코드: ${e.code()}", e)
            } catch (e: Exception) {
                Log.e("TAG", "getDirecGp: 알 수 없는 오류", e)
            }
        }
    }
}