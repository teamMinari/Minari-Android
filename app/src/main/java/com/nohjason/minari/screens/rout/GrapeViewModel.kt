package com.nohjason.minari.screens.rout

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nohjason.minari.network.ApiService
import com.nohjason.minari.network.response.GetAllLikesTerm
import com.nohjason.minari.network.response.GetTerm
import com.nohjason.minari.network.response.TermLikes
import com.nohjason.minari.network.response.rout.Grape
import com.nohjason.minari.network.response.rout.GrapeSeed
import com.nohjason.minari.network.response.rout.Grapes
import com.nohjason.minari.network.response.rout.GrapesAll
import com.nohjason.minari.preferences.PreferencesManager
import com.nohjason.minari.screens.rout.response.LikesResponse
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
class GrapeViewModel @Inject constructor(
    application: Application,
    private val api: ApiService
) : AndroidViewModel(application) {

    private val preferencesManager = PreferencesManager(application)

    private val _route = MutableStateFlow<GrapesAll?>(null)
    val route: StateFlow<GrapesAll?> = _route

    fun getAllGps() {
        viewModelScope.launch {
            val token = preferencesManager.getToken()
            if (token.isNullOrEmpty()) {
                Log.e("TAG", "getAllGps: 토큰 없음")
                return@launch
            }
            try {
                val response = withContext(Dispatchers.IO) {
                    api.getAllGps(token = token)
                }
                if (response.isSuccessful) {
                    _route.value = response.body()
                    Log.d("TAG", "getAllGps: 전체 포도송이 서버 통신 성공")
                } else {
                    Log.e("TAG", "getAllGps: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: IOException) {
                Log.e("TAG", "getAllGps: 네트워크 오류", e)
            } catch (e: HttpException) {
                Log.e("TAG", "getAllGps: HTTP 오류 - 코드: ${e.code()}", e)
            } catch (e: Exception) {
                Log.e("TAG", "getAllGps: 알 수 없는 오류", e)
            }
        }
    }

    private val _gpsDetail = MutableStateFlow<Grapes?>(null)
    val gpsDetail: StateFlow<Grapes?> = _gpsDetail

    fun getGps(gpsId: Int) {
        viewModelScope.launch {
            val token = preferencesManager.getToken()
            if (token.isNullOrEmpty()) {
                Log.e("TAG", "getGps: 토큰 없음")
                return@launch
            }
            try {
                val response = withContext(Dispatchers.IO) {
                    api.getGps(token = token, gpsId = gpsId)
                }
                if (response.isSuccessful) {
                    _gpsDetail.value = response.body()
                    Log.d("TAG", "getGps: 포도알 서버 통신 성공")
                } else {
                    Log.e("TAG", "getGps: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: IOException) {
                Log.e("TAG", "getGps: 네트워크 오류", e)
            } catch (e: HttpException) {
                Log.e("TAG", "getGps: HTTP 오류 - 코드: ${e.code()}", e)
            } catch (e: Exception) {
                Log.e("TAG", "getGps: 알 수 없는 오류", e)
            }
        }
    }

    private val _grape = MutableStateFlow<Grape?>(null)
    val allGp: StateFlow<Grape?> = _grape

    fun getAllGrape(gpId: Int) {
        viewModelScope.launch {
            val token = preferencesManager.getToken()
            if (token.isNullOrEmpty()) {
                Log.e("TAG", "getAllGrape: 토큰 없음")
                return@launch
            }
            try {
                val response = withContext(Dispatchers.IO) {
                    api.getAllGrape(token = token, gpId = gpId)
                }
                if (response.isSuccessful) {
                    _grape.value = response.body()
                    Log.d("TAG", "getAllGrape: 모든 포도씨 서버 통신 성공")
                } else {
                    Log.e("TAG", "getAllGrape: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: IOException) {
                Log.e("TAG", "getAllGrape: 네트워크 오류", e)
            } catch (e: HttpException) {
                Log.e("TAG", "getAllGrape: HTTP 오류 - 코드: ${e.code()}", e)
            } catch (e: Exception) {
                Log.e("TAG", "getAllGrape: 알 수 없는 오류", e)
            }
        }
    }

    private val _gpse = MutableStateFlow<GrapeSeed?>(null)
    val gpse: StateFlow<GrapeSeed?> = _gpse

    fun getGpse(gpseId: Int) {
        viewModelScope.launch {
            val token = preferencesManager.getToken()
            if (token.isNullOrEmpty()) {
                Log.e("TAG", "getGpse: 토큰 없음")
                return@launch
            }
            try {
                val response = withContext(Dispatchers.IO) {
                    api.getGpse(token = token, gpseId = gpseId)
                }
                if (response.isSuccessful) {
                    _gpse.value = response.body()
                    Log.d("TAG", "getGpse: 포도씨 서버 통신 성공")
                } else {
                    Log.e("TAG", "getGpse: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: IOException) {
                Log.e("TAG", "getGpse: 네트워크 오류", e)
            } catch (e: HttpException) {
                Log.e("TAG", "getGpse: HTTP 오류 - 코드: ${e.code()}", e)
            } catch (e: Exception) {
                Log.e("TAG", "getGpse: 알 수 없는 오류", e)
            }
        }
    }

    private val _likes = MutableStateFlow<LikesResponse?>(null)
    val likes: StateFlow<LikesResponse?> = _likes

    fun likes(category: String, id: Int, termNm: String = "") {
        viewModelScope.launch {
            val token = preferencesManager.getToken()
            if (token.isNullOrEmpty()) {
                Log.e("TAG", "likes: 토큰 없음")
                return@launch
            }
            try {
                val response = withContext(Dispatchers.IO) {
                    api.likes(token, category, id)
                }
                if (response.isSuccessful) {
                    _likes.value = response.body()
                    Log.d("TAG", "likesGpse: 좋아요 서버 통신 성공")
                    // 좋아요 후 데이터 갱신
                    when (category) {
                        "TERM" -> getTerm(termNm)
                        "GRAPES" -> getAllGps()
                        "GRAPE" -> getGps(id)
                        "GRAPESEED" -> getGpse(id)
                    }
                } else {
                    Log.e("TAG", "likesGpse: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: IOException) {
                Log.e("TAG", "likesGpse: 네트워크 오류", e)
            } catch (e: HttpException) {
                Log.e("TAG", "likesGpse: HTTP 오류 - 코드: ${e.code()}", e)
            } catch (e: Exception) {
                Log.e("TAG", "likesGpse: 알 수 없는 오류", e)
            }
        }
    }

    private val _getTerm = MutableStateFlow<GetTerm?>(null)
    val getTerm: StateFlow<GetTerm?> = _getTerm

    fun getTerm(termNm: String) {
        viewModelScope.launch {
            val token = preferencesManager.getToken()
            if (token.isNullOrEmpty()) {
                Log.e("TAG", "getTerm: 토큰 없음")
                return@launch
            }
            try {
                val response = withContext(Dispatchers.IO) {
                    api.getTerm(token, termNm)
                }
                if (response.isSuccessful) {
                    _getTerm.value = response.body()
                    Log.d("TAG", "getTerm: 단일 용어 조회 서버 통신 성공")
                } else {
                    Log.e("TAG", "getTerm: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: IOException) {
                Log.e("TAG", "getTerm: 네트워크 오류", e)
            } catch (e: HttpException) {
                Log.e("TAG", "getTerm: HTTP 오류 - 코드: ${e.code()}", e)
            } catch (e: Exception) {
                Log.e("TAG", "getTerm: 알 수 없는 오류", e)
            }
        }
    }

    private val _getAllLikesTerm = MutableStateFlow<GetAllLikesTerm?>(null)
    val getAllLikesTerm: StateFlow<GetAllLikesTerm?> = _getAllLikesTerm

    fun getAllLikesTerm() {
        viewModelScope.launch {
            val token = preferencesManager.getToken()
            if (token.isNullOrEmpty()) {
                Log.e("TAG", "getAllLikesTerm: 토큰 없음")
                return@launch
            }
            try {
                val response = withContext(Dispatchers.IO) {
                    api.getAllLikesTerm(token)
                }
                if (response.isSuccessful) {
                    _getAllLikesTerm.value = response.body()
                    Log.d("TAG", "getAllLikesTerm: 좋아요 서버 통신 성공")
                } else {
                    Log.e("TAG", "getAllLikesTerm: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: IOException) {
                Log.e("TAG", "getAllLikesTerm: 네트워크 오류", e)
            } catch (e: HttpException) {
                Log.e("TAG", "getAllLikesTerm: HTTP 오류 - 코드: ${e.code()}", e)
            } catch (e: Exception) {
                Log.e("TAG", "getAllLikesTerm: 알 수 없는 오류", e)
            }
        }
    }
}




//@HiltViewModel
//class GrapeViewModel @Inject constructor(
//    application: Application
//) : AndroidViewModel(application) {
//
//    private val preferencesManager = PreferencesManager(application)
//
//    private val _route = MutableStateFlow<GrapesAll?>(null)
//    val route: StateFlow<GrapesAll?> = _route
//
//    fun getAllGps() {
//        viewModelScope.launch {
//            val token = preferencesManager.getToken()
//            if (token.isNullOrEmpty()) {
//                Log.e("TAG", "getAllGps: 토큰 없음")
//                return@launch
//            }
//            try {
//                val response = withContext(Dispatchers.IO) {
//                    api.getAllGps(token = token)
//                }
//                if (response.isSuccessful) {
//                    _route.value = response.body()
//                    Log.d("TAG", "getAllGps: 전체 포도송이 서버 통신 성공")
//                } else {
//                    Log.e("TAG", "getAllGps: 서버 응답 에러 - 코드: ${response.code()}")
//                }
//            } catch (e: IOException) {
//                Log.e("TAG", "getAllGps: 네트워크 오류", e)
//            } catch (e: HttpException) {
//                Log.e("TAG", "getAllGps: HTTP 오류 - 코드: ${e.code()}", e)
//            } catch (e: Exception) {
//                Log.e("TAG", "getAllGps: 알 수 없는 오류", e)
//            }
//        }
//    }
//
//    private val _gpsDetail = MutableStateFlow<Grapes?>(null)
//    val gpsDetail: StateFlow<Grapes?> = _gpsDetail
//
//    fun getGps(gpsId: Int) {
//        viewModelScope.launch {
//            val token = preferencesManager.getToken()
//            if (token.isNullOrEmpty()) {
//                Log.e("TAG", "getGps: 토큰 없음")
//                return@launch
//            }
//            try {
//                val response = withContext(Dispatchers.IO) {
//                    api.getGps(token = token, gpsId = gpsId)
//                }
//                if (response.isSuccessful) {
//                    _gpsDetail.value = response.body()
//                    Log.d("TAG", "getGps: 포도알 서버 통신 성공")
//                } else {
//                    Log.e("TAG", "getGps: 서버 응답 에러 - 코드: ${response.code()}")
//                }
//            } catch (e: IOException) {
//                Log.e("TAG", "getGps: 네트워크 오류", e)
//            } catch (e: HttpException) {
//                Log.e("TAG", "getGps: HTTP 오류 - 코드: ${e.code()}", e)
//            } catch (e: Exception) {
//                Log.e("TAG", "getGps: 알 수 없는 오류", e)
//            }
//        }
//    }
//
//    private val _grape = MutableStateFlow<Grape?>(null)
//    val allGp: StateFlow<Grape?> = _grape
//
//    fun getAllGrape(gpId: Int) {
//        viewModelScope.launch {
//            val token = preferencesManager.getToken()
//            if (token.isNullOrEmpty()) {
//                Log.e("TAG", "getAllGrape: 토큰 없음")
//                return@launch
//            }
//            try {
//                val response = withContext(Dispatchers.IO) {
//                    api.getAllGrape(token = token, gpId = gpId)
//                }
//                if (response.isSuccessful) {
//                    _grape.value = response.body()
//                    Log.d("TAG", "getAllGrape: 모든 포도씨 서버 통신 성공")
//                } else {
//                    Log.e("TAG", "getAllGrape: 서버 응답 에러 - 코드: ${response.code()}")
//                }
//            } catch (e: IOException) {
//                Log.e("TAG", "getAllGrape: 네트워크 오류", e)
//            } catch (e: HttpException) {
//                Log.e("TAG", "getAllGrape: HTTP 오류 - 코드: ${e.code()}", e)
//            } catch (e: Exception) {
//                Log.e("TAG", "getAllGrape: 알 수 없는 오류", e)
//            }
//        }
//    }
//
//    private val _gpse = MutableStateFlow<GrapeSeed?>(null)
//    val gpse: StateFlow<GrapeSeed?> = _gpse
//
//    fun getGpse(gpseId: Int) {
//        viewModelScope.launch {
//            val token = preferencesManager.getToken()
//            if (token.isNullOrEmpty()) {
//                Log.e("TAG", "getGpse: 토큰 없음")
//                return@launch
//            }
//            try {
//                val response = withContext(Dispatchers.IO) {
//                    api.getGpse(token = token, gpseId = gpseId)
//                }
//                if (response.isSuccessful) {
//                    _gpse.value = response.body()
//                    Log.d("TAG", "getGpse: 포도씨 서버 통신 성공")
//                } else {
//                    Log.e("TAG", "getGpse: 서버 응답 에러 - 코드: ${response.code()}")
//                }
//            } catch (e: IOException) {
//                Log.e("TAG", "getGpse: 네트워크 오류", e)
//            } catch (e: HttpException) {
//                Log.e("TAG", "getGpse: HTTP 오류 - 코드: ${e.code()}", e)
//            } catch (e: Exception) {
//                Log.e("TAG", "getGpse: 알 수 없는 오류", e)
//            }
//        }
//    }
//
//    private val _likes = MutableStateFlow<LikesResponse?>(null)
//    val likes: StateFlow<LikesResponse?> = _likes
//
//    fun likes(category: String, id: Int, termNm: String = "") {
//        viewModelScope.launch {
//            val token = preferencesManager.getToken()
//            if (token.isNullOrEmpty()) {
//                Log.e("TAG", "likes: 토큰 없음")
//                return@launch
//            }
//            try {
//                val response = withContext(Dispatchers.IO) {
//                    api.likes(token, category, id)
//                }
//                if (response.isSuccessful) {
//                    _likes.value = response.body()
//                    Log.d("TAG", "likesGpse: 좋아요 서버 통신 성공")
//                    // 좋아요 후 데이터 갱신
//                    when (category) {
//                        "TERM" -> getTerm(termNm)
//                        "GRAPES" -> getAllGps()
//                        "GRAPE" -> getGps(id)
//                        "GRAPESEED" -> getGpse(id)
//                    }
//                } else {
//                    Log.e("TAG", "likesGpse: 서버 응답 에러 - 코드: ${response.code()}")
//                }
//            } catch (e: IOException) {
//                Log.e("TAG", "likesGpse: 네트워크 오류", e)
//            } catch (e: HttpException) {
//                Log.e("TAG", "likesGpse: HTTP 오류 - 코드: ${e.code()}", e)
//            } catch (e: Exception) {
//                Log.e("TAG", "likesGpse: 알 수 없는 오류", e)
//            }
//        }
//    }
//
//    private val _getTerm = MutableStateFlow<GetTerm?>(null)
//    val getTerm: StateFlow<GetTerm?> = _getTerm
//
//    fun getTerm(termNm: String) {
//        viewModelScope.launch {
//            val token = preferencesManager.getToken()
//            if (token.isNullOrEmpty()) {
//                Log.e("TAG", "getTerm: 토큰 없음")
//                return@launch
//            }
//            try {
//                val response = withContext(Dispatchers.IO) {
//                    api.getTerm(token, termNm)
//                }
//                if (response.isSuccessful) {
//                    _getTerm.value = response.body()
//                    Log.d("TAG", "getTerm: 단일 용어 조회 서버 통신 성공")
//                } else {
//                    Log.e("TAG", "getTerm: 서버 응답 에러 - 코드: ${response.code()}")
//                }
//            } catch (e: IOException) {
//                Log.e("TAG", "getTerm: 네트워크 오류", e)
//            } catch (e: HttpException) {
//                Log.e("TAG", "getTerm: HTTP 오류 - 코드: ${e.code()}", e)
//            } catch (e: Exception) {
//                Log.e("TAG", "getTerm: 알 수 없는 오류", e)
//            }
//        }
//    }
//
//    private val _getAllLikesTerm = MutableStateFlow<GetAllLikesTerm?>(null)
//    val getAllLikesTerm: StateFlow<GetAllLikesTerm?> = _getAllLikesTerm
//
//    fun getAllLikesTerm() {
//        viewModelScope.launch {
//            val token = preferencesManager.getToken()
//            if (token.isNullOrEmpty()) {
//                Log.e("TAG", "getAllLikesTerm: 토큰 없음")
//                return@launch
//            }
//            try {
//                val response = withContext(Dispatchers.IO) {
//                    api.getAllLikesTerm(token)
//                }
//                if (response.isSuccessful) {
//                    _getAllLikesTerm.value = response.body()
//                    Log.d("TAG", "getAllLikesTerm: 좋아요 서버 통신 성공")
//                } else {
//                    Log.e("TAG", "getAllLikesTerm: 서버 응답 에러 - 코드: ${response.code()}")
//                }
//            } catch (e: IOException) {
//                Log.e("TAG", "getAllLikesTerm: 네트워크 오류", e)
//            } catch (e: HttpException) {
//                Log.e("TAG", "getAllLikesTerm: HTTP 오류 - 코드: ${e.code()}", e)
//            } catch (e: Exception) {
//                Log.e("TAG", "getAllLikesTerm: 알 수 없는 오류", e)
//            }
//        }
//    }
//}
