package com.nohjason.minari.network

import com.nohjason.minari.preferences.PreferencesManager
import com.nohjason.minari.screens.auth.data.model.RefreshTokenRequest
import com.nohjason.myapplication.network.RetrofitInstance.api
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator(
    private val preferencesManager: PreferencesManager,
    private val syncApiService: ApiService
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val refreshToken = preferencesManager.getRefreshToken() ?: return null
        val refreshResponse = syncApiService.refreshTokenSync(RefreshTokenRequest(refreshToken)).execute()
        return if (refreshResponse.isSuccessful) {
            val newAccessToken = refreshResponse.body()?.data?.accessToken ?: return null
            preferencesManager.saveToken(newAccessToken)
            response.request.newBuilder()
                .header("Authorization", "Bearer $newAccessToken")
                .build()
        } else {
            null
        }
    }
}

