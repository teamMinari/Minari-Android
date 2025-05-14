package com.nohjason.minari.screens.rout

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nohjason.cheongfordo.network.response.rout.Grape
import com.nohjason.cheongfordo.network.response.rout.GrapeSeed
import com.nohjason.cheongfordo.network.response.rout.Grapes
import com.nohjason.cheongfordo.network.response.rout.GrapesAll
import com.nohjason.cheongfordo.screens.rout.response.LikesResponse
import com.nohjason.cheongfordo.network.ApiService
import com.nohjason.cheongfordo.network.response.GetAllLikesTerm
import com.nohjason.cheongfordo.network.response.GetSearchTerm
import com.nohjason.cheongfordo.network.response.GetTerm
import com.nohjason.cheongfordo.preferences.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GrapeViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager,
    application: Application,
    private val api: ApiService
) : AndroidViewModel(application) {

    private val _route = MutableStateFlow<GrapesAll?>(null)
    val route: StateFlow<GrapesAll?> = _route

    fun getAllGps() {
        viewModelScope.launch {
            val token = preferencesManager.getToken()
            if (token.isNullOrEmpty()) {
                Log.e("GrapeViewModel", "getAllGps: 토큰 없음")
                return@launch
            }
            try {
                val response = withContext(Dispatchers.IO) { api.getAllGps(token) }
                if (response.isSuccessful) {
                    _route.value = response.body()
                    Log.d("GrapeViewModel", "getAllGps: 전체 포도송이 서버 통신 성공")
                } else {
                    Log.e("GrapeViewModel", "getAllGps: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("GrapeViewModel", "getAllGps: 오류 발생", e)
            }
        }
    }

    private val _categoryRoute = MutableStateFlow<GrapesAll?>(null)
    val categoryRoute: StateFlow<GrapesAll?> = _categoryRoute

    fun getGpsByCategory(age: String, work: String) {
        viewModelScope.launch {
            val token = preferencesManager.getToken()
            if (token.isNullOrEmpty()) {
                Log.e("GrapeViewModel", "getGpsByCategory: 토큰 없음")
                return@launch
            }
            try {
                val response = withContext(Dispatchers.IO) { api.getGpsCategory(token, age, work) }
                if (response.isSuccessful) {
                    _categoryRoute.value = response.body()
                    Log.d("GrapeViewModel", "getGpsByCategory: 카테고리별 포도송이 서버 통신 성공")
                } else {
                    Log.e("GrapeViewModel", "getGpsByCategory: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("GrapeViewModel", "getGpsByCategory: 오류 발생", e)
            }
        }
    }

    private val _gpsDetail = MutableStateFlow<Grapes?>(null)
    val gpsDetail: StateFlow<Grapes?> = _gpsDetail

    fun getGps(gpsId: Int) {
        viewModelScope.launch {
            val token = preferencesManager.getToken()
            if (token.isNullOrEmpty()) {
                Log.e("GrapeViewModel", "getGps: 토큰 없음")
                return@launch
            }
            try {
                val response = withContext(Dispatchers.IO) { api.getGps(token, gpsId) }
                if (response.isSuccessful) {
                    _gpsDetail.value = response.body()
                    Log.d("GrapeViewModel", "getGps: 포도알 서버 통신 성공")
                } else {
                    Log.e("GrapeViewModel", "getGps: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("GrapeViewModel", "getGps: 오류 발생", e)
            }
        }
    }

    private val _grape = MutableStateFlow<Grape?>(null)
    val allGp: StateFlow<Grape?> = _grape

    fun getAllGrape(gpId: Int) {
        viewModelScope.launch {
            val token = preferencesManager.getToken()
            if (token.isNullOrEmpty()) {
                Log.e("GrapeViewModel", "getAllGrape: 토큰 없음")
                return@launch
            }
            try {
                val response = withContext(Dispatchers.IO) { api.getAllGrape(token, gpId) }
                if (response.isSuccessful) {
                    _grape.value = response.body()
                    Log.d("GrapeViewModel", "getAllGrape: 모든 포도씨 서버 통신 성공")
                } else {
                    Log.e("GrapeViewModel", "getAllGrape: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("GrapeViewModel", "getAllGrape: 오류 발생", e)
            }
        }
    }

    private val _gpse = MutableStateFlow<GrapeSeed?>(null)
    val gpse: StateFlow<GrapeSeed?> = _gpse

    fun getGpse(gpseId: Int) {
        viewModelScope.launch {
            val token = preferencesManager.getToken()
            if (token.isNullOrEmpty()) {
                Log.e("GrapeViewModel", "getGpse: 토큰 없음")
                return@launch
            }
            try {
                val response = withContext(Dispatchers.IO) { api.getGpse(token, gpseId) }
                if (response.isSuccessful) {
                    _gpse.value = response.body()
                    Log.d("GrapeViewModel", "getGpse: 포도씨 서버 통신 성공")
                } else {
                    Log.e("GrapeViewModel", "getGpse: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("GrapeViewModel", "getGpse: 오류 발생", e)
            }
        }
    }

    private val _likes = MutableStateFlow<LikesResponse?>(null)
    val likes: StateFlow<LikesResponse?> = _likes

    fun likes(category: String, id: Int, termNm: String = "") {
        viewModelScope.launch {
            val token = preferencesManager.getToken()
            if (token.isNullOrEmpty()) {
                Log.e("GrapeViewModel", "likes: 토큰 없음")
                return@launch
            }
            try {
                val response = withContext(Dispatchers.IO) { api.likes(token, category, id) }
                if (response.isSuccessful) {
                    _likes.value = response.body()
                    Log.d("GrapeViewModel", "likes: 좋아요 서버 통신 성공")
                    when (category) {
//                        "TERM" -> getTerm(termNm)
                        "GRAPES" -> getAllGps()
                        "GRAPE" -> getGps(id)
                        "GRAPESEED" -> getGpse(id)
                    }
                } else {
                    Log.e("GrapeViewModel", "likes: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("GrapeViewModel", "likes: 오류 발생", e)
            }
        }
    }

    //    private val _getTerm = MutableStateFlow<GetTerm?>(null) // 초기값은 null로 설정
//    val getTerm: StateFlow<GetTerm?> = _getTerm
//
//    fun getTerm(token: String, termNm: String) {
//        viewModelScope.launch {
//            try {
//                val response = withContext(Dispatchers.IO) {
//                    api.getTerm(token, termNm)
//                }
//                if (response.isSuccessful) {
//                    _getTerm.value = response.body()
//                    Log.d("TAG", "getTerm: 단일 용어 조회 서버 통신 성공")
//                } else {
//                    // 서버 응답 에러 처리
//                    Log.e("TAG", "getTerm: 서버 응답 에러 - 코드: ${response.code()}")
//                }
//            } catch (e: IOException) {
//                // 네트워크 오류 처리
//                Log.e("TAG", "getTerm: 네트워크 오류", e)
//            } catch (e: HttpException) {
//                // HTTP 오류 처리
//                Log.e("TAG", "getTerm: HTTP 오류 - 코드: ${e.code()}", e)
//            } catch (e: Exception) {
//                // 기타 예외 처리
//                Log.e("TAG", "getTerm: 알 수 없는 오류", e)
//            }
//        }
//    }

    private val _getAllLikesTerm = MutableStateFlow<GetAllLikesTerm?>(null)
    val getAllLikesTerm: StateFlow<GetAllLikesTerm?> = _getAllLikesTerm

    fun getAllLikesTerm() {
        viewModelScope.launch {
            val token = preferencesManager.getToken()
            if (token.isNullOrEmpty()) {
                Log.e("GrapeViewModel", "getAllLikesTerm: 토큰 없음")
                return@launch
            }
            try {
                val response = withContext(Dispatchers.IO) { api.getAllLikesTerm(token) }
                if (response.isSuccessful) {
                    _getAllLikesTerm.value = response.body()
                    Log.d("GrapeViewModel", "getAllLikesTerm: 좋아요 서버 통신 성공")
                } else {
                    Log.e("GrapeViewModel", "getAllLikesTerm: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("GrapeViewModel", "getAllLikesTerm: 오류 발생", e)
            }
        }
    }

    private val _getSearchTerm = MutableStateFlow<GetSearchTerm?>(null)
    val getSearchTerm: StateFlow<GetSearchTerm?> = _getSearchTerm

    fun getSearchTerm(termNm: String) {
        viewModelScope.launch {
            val token = preferencesManager.getToken()
            if (token.isNullOrEmpty()) {
                Log.e("GrapeViewModel", "getSearchTerm: 토큰 없음")
                return@launch
            }
            try {
                val response = withContext(Dispatchers.IO) { api.getSearchTerm(token, termNm) }
                if (response.isSuccessful) {
                    _getSearchTerm.value = response.body()
                    Log.d("GrapeViewModel", "getSearchTerm: 서버 통신 성공")
                } else {
                    Log.e("GrapeViewModel", "getSearchTerm: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("GrapeViewModel", "getSearchTerm: 오류 발생", e)
            }
        }
    }

    private val _getEasyTerm = MutableStateFlow<String?>(null)
    val getEasyTerm: StateFlow<String?> = _getEasyTerm

    fun getEasyTerm(termNm: String) {
        viewModelScope.launch {
            val token = preferencesManager.getToken()
            if (token.isNullOrEmpty()) {
                Log.e("GrapeViewModel", "getEasyTerm: 토큰 없음")
                return@launch
            }
            try {
                val response = withContext(Dispatchers.IO) { api.getEasyTerm(token, termNm) }
                if (response.isSuccessful) {
                    _getEasyTerm.value = response.body()?.string()
                    Log.d("GrapeViewModel", "getEasyTerm: 서버 통신 성공")
                } else {
                    Log.e("GrapeViewModel", "getEasyTerm: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("GrapeViewModel", "getEasyTerm: 오류 발생", e)
            }
        }
    }
}
