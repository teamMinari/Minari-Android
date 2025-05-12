package com.nohjason.minari.preferences

import android.content.Context
import android.content.SharedPreferences
import com.nohjason.minari.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

class PreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_TOKEN = "key_token"
        private const val KEY_REFRESH_TOKEN = "key_refresh_token"
        private const val KEY_AUTO_LOGIN = "key_auto_login"
    }

    fun saveToken(token: String) {
        sharedPreferences.edit().putString(KEY_TOKEN, token).apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString(KEY_TOKEN, null)
    }

    fun saveRefreshToken(refreshToken: String) {
        sharedPreferences.edit().putString(KEY_REFRESH_TOKEN, refreshToken).apply()
    }

    fun getRefreshToken(): String? {
        return sharedPreferences.getString(KEY_REFRESH_TOKEN, null)
    }

    fun setAutoLogin(enabled: Boolean) {
        sharedPreferences.edit().putBoolean(KEY_AUTO_LOGIN, enabled).apply()
    }

    fun isAutoLogin(): Boolean {
        return sharedPreferences.getBoolean(KEY_AUTO_LOGIN, false)
    }

    fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }
}


