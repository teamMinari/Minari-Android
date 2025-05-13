package com.nohjason.cheongfordo.core

import com.nohjason.cheongfordo.network.ApiService
import com.nohjason.cheongfordo.preferences.PreferencesManager
import com.nohjason.myapplication.network.AuthApiService
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(
    private val preferencesManager: PreferencesManager,
    @AuthApiService private val apiService: ApiService
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        // apiService를 직접 사용
        return null
    }
}

//class TokenAuthenticator @Inject constructor(
//    private val preferencesManager: PreferencesManager,
//    private val apiServiceProvider: javax.inject.Provider<ApiService>
//) : Authenticator {
//
//    override fun authenticate(route: Route?, response: Response): Request? {
//        // 인증 실패가 2번 이상이면 중단 (무한 루프 방지)
//        if (responseCount(response) >= 2) {
//            return null
//        }
//
//        val apiService = apiServiceProvider.get()
//
//        // 저장된 리프레시 토큰 가져오기
//        val refreshToken = preferencesManager.getRefreshToken() ?: return null
//
//        // 토큰 갱신 동기 호출 (runBlocking 사용)
//        val newAccessToken = runBlocking {
//            try {
//                val tokenResponse = apiService.refreshToken(RefreshTokenRequest(refreshToken))
//                if (tokenResponse.isSuccessful) {
////                    tokenResponse.body()?.accessToken
//                } else {
//                    null
//                }
//            } catch (e: Exception) {
//                null
//            }
//        } ?: return null // 토큰 갱신 실패 시 null 반환
//
//        // 새 액세스 토큰 저장
////        preferencesManager.saveToken(newAccessToken)
//
//        // 기존 요청을 새 토큰으로 재구성하여 반환
//        return response.request.newBuilder()
//            .header("Authorization", "Bearer $newAccessToken")
//            .build()
//    }
//
//    // 인증 실패 횟수 계산 함수 (무한 루프 방지용)
//    private fun responseCount(response: Response): Int {
//        var result = 1
//        var priorResponse = response.priorResponse
//        while (priorResponse != null) {
//            result++
//            priorResponse = priorResponse.priorResponse
//        }
//        return result
//    }
//}
