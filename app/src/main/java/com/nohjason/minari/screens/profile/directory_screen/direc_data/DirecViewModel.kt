package com.nohjason.minari.screens.profile.directory_screen.direc_data

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjason.minari.preferences.PreferencesManager
import com.nohjason.myapplication.network.RetrofitInstance
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DirecViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    private val preferencesManager = PreferencesManager(application)

    // 용어
    private val _direcTermData = MutableStateFlow<DirecTermResponse?>(null)
    val direcTermData: StateFlow<DirecTermResponse?> get() = _direcTermData

    fun getDirecTerm() {
        viewModelScope.launch {
            val token = preferencesManager.getToken()
            if (token != null) {
                try {
                    val response = RetrofitInstance.api.getDiercTerm(token)
                    _direcTermData.value = response
                    Log.d("TAG", "getDirecTerm: 저장목록 용어 서버 통신 성공")
                } catch (e: Exception) {
                    Log.e("TAG", "getDirecTerm: 저장목록 용어 오류", e)
                }
            } else {
                Log.e("TAG", "getDirecTerm: 토큰 없음")
            }
        }
    }

    // 포도씨
    private val _direcGpseData = MutableStateFlow<DirecGpseResponse?>(null)
    val direcGpseData: StateFlow<DirecGpseResponse?> get() = _direcGpseData

    fun getDirecGpse() {
        viewModelScope.launch {
            val token = preferencesManager.getToken()
            if (token != null) {
                try {
                    val response = RetrofitInstance.api.getDiercGpse(token)
                    _direcGpseData.value = response
                    Log.d("TAG", "getDirecGpse: 저장목록 포도씨 서버 통신 성공")
                } catch (e: Exception) {
                    Log.e("TAG", "getDirecGpse: 저장목록 포도씨 오류", e)
                }
            } else {
                Log.e("TAG", "getDirecGpse: 토큰 없음")
            }
        }
    }

    // 포도알
    private val _direcGpsData = MutableStateFlow<DirecGpsResponse?>(null)
    val direcGpsData: StateFlow<DirecGpsResponse?> get() = _direcGpsData

    fun getDirecGps() {
        viewModelScope.launch {
            val token = preferencesManager.getToken()
            if (token != null) {
                try {
                    val response = RetrofitInstance.api.getDiercGps(token)
                    _direcGpsData.value = response
                    Log.d("TAG", "getDirecGps: 저장목록 포도알 서버 통신 성공")
                } catch (e: Exception) {
                    Log.e("TAG", "getDirecGps: 저장목록 포도알 오류", e)
                }
            } else {
                Log.e("TAG", "getDirecGps: 토큰 없음")
            }
        }
    }

    // 포도송이
    private val _direcGpData = MutableStateFlow<DirecGpResponse?>(null)
    val direcGpData: StateFlow<DirecGpResponse?> get() = _direcGpData

    fun getDirecGp() {
        viewModelScope.launch {
            val token = preferencesManager.getToken()
            if (token != null) {
                try {
                    val response = RetrofitInstance.api.getDierctGp(token)
                    _direcGpData.value = response
                    Log.d("TAG", "getDirecGp: 저장목록 포도송이 서버 통신 성공")
                    println("Gp임 $response")
                } catch (e: Exception) {
                    Log.e("TAG", "getDirecGp: 저장목록 포도송이 오류", e)
                }
            } else {
                Log.e("TAG", "getDirecGp: 토큰 없음")
            }
        }
    }
}
