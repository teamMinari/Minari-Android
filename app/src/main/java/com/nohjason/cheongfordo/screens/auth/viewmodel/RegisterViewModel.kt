package com.nohjason.cheongfordo.screens.auth.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjason.cheongfordo.network.ApiService
import com.nohjason.cheongfordo.screens.auth.data.model.RegisterRequest
import com.nohjason.cheongfordo.screens.auth.data.model.RegisterResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    private val _registerResponse = MutableStateFlow<RegisterResponse?>(null)
    val registerResponse: StateFlow<RegisterResponse?> = _registerResponse

    var id by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var confirmPassword by mutableStateOf("")
        private set
    var email by mutableStateOf("")
        private set

    fun updateId(newId: String) {
        id = newId
    }

    fun updatePassword(newPassword: String) {
        password = newPassword
    }

    fun updateConfirmPassword(newConfirmPassword: String) {
        confirmPassword = newConfirmPassword
    }

    fun updateEmail(newEmail: String) {
        email = newEmail
    }

    fun register() {
        viewModelScope.launch {
            try {
                val response = apiService.register(RegisterRequest(id, password, confirmPassword, email))
                _registerResponse.value = response
                Log.d("TAG", "register: 회원가입 성공")
            } catch (e: Exception) {
                Log.e("TAG", "register: $e")
                _registerResponse.value = RegisterResponse(
                    success = false,
                    status = "",
                    message = e.message ?: "알 수 없는 오류"
                )
            }
        }
    }
}

//@HiltViewModel
//class RegisterViewModel @Inject constructor() : ViewModel() {
////    class RegisterViewModel : ViewModel() {
//    private val _registerResponse = MutableStateFlow<RegisterResponse?>(null)
//    val registerResponse: StateFlow<RegisterResponse?> = _registerResponse
//
//    var id by mutableStateOf("")
//        private set
//    var password by mutableStateOf("")
//        private set
//    var confirmPassword by mutableStateOf("")
//        private set
//    var email by mutableStateOf("")
//        private set
//
//    fun updateId(newId: String) {
//        id = newId
//    }
//
//    fun updatePassword(newPassword: String) {
//        password = newPassword
//    }
//
//    fun updateConfirmPassword(newConfirmPassword: String) {
//        confirmPassword = newConfirmPassword
//    }
//
//    fun updateEmail(newEmail: String) {
//        email = newEmail
//    }
//
//
//    fun register() {
//        viewModelScope.launch {
//            try {
//                val response = api.register(RegisterRequest(id, password, confirmPassword, email))
//                _registerResponse.value = response
//                Log.d("TAG", "register: 회원가입 성공")
//            } catch (e: Exception) {
//                Log.e("TAG", "register: $e")
//                _registerResponse.value = RegisterResponse(
//                    success = false,
//                    status = "",
//                    message = e.message.toString(),
//                )
//            }
//        }
//    }
//}
