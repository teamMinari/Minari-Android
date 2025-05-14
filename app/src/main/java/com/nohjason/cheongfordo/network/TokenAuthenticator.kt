package com.nohjason.cheongfordo.network

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