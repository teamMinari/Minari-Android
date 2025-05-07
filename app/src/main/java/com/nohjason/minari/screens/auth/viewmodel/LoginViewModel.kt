package com.nohjason.minari.screens.auth.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjason.minari.screens.auth.data.model.LoginRequest
import com.nohjason.minari.screens.auth.data.model.LoginResponse
import com.nohjason.minari.screens.auth.data.model.RefreshTokenRequest
import com.nohjason.minari.screens.auth.data.model.RefreshTokenResponse
import com.nohjason.myapplication.network.RetrofitInstance.api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class LoginViewModel : ViewModel() {
    private val _refreshResult = MutableStateFlow<RefreshTokenResponse?>(null)
    val refreshResult: StateFlow<RefreshTokenResponse?> = _refreshResult

    fun refreshToken(refreshToken: String) {
        Log.d("TAG", "refreshToken() called with refreshToken: $refreshToken")
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    api.refreshToken(RefreshTokenRequest(refreshToken))
                }
                if (response.isSuccessful) {
                    _refreshResult.value = response.body()
                    Log.d("TAG", "refreshToken: 토큰 갱신 성공")
                } else {
                    Log.e("TAG", "refreshToken: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: IOException) {
                Log.e("TAG", "refreshToken: 네트워크 오류", e)
            } catch (e: HttpException) {
                Log.e("TAG", "refreshToken: HTTP 오류 - 코드: ${e.code()}", e)
            } catch (e: Exception) {
                Log.e("TAG", "refreshToken: 알 수 없는 오류", e)
            }
        }
    }

    private val _loginRequest = MutableStateFlow<LoginResponse?>(null)
    val loginRequest: StateFlow<LoginResponse?> = _loginRequest

    fun login(id: String, password: String) {
        Log.d("TAG", "login() called with id: $id and password: $password")
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    api.login(LoginRequest(id, password))
                }
                if (response.isSuccessful) {
                    _loginRequest.value = response.body()
                    Log.d("TAG", "login: 로그인 서버 통신 성공")
                } else {
                    // 서버 응답 에러 처리
                    Log.e("TAG", "login: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: IOException) {
                // 네트워크 오류 처리
                Log.e("TAG", "login: 네트워크 오류", e)
            } catch (e: HttpException) {
                // HTTP 오류 처리
                Log.e("TAG", "login: HTTP 오류 - 코드: ${e.code()}", e)
            } catch (e: Exception) {
                // 기타 예외 처리
                Log.e("TAG", "login: 알 수 없는 오류", e)
            }
        }
    }
}



