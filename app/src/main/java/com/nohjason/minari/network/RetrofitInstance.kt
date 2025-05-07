package com.nohjason.myapplication.network

import com.nohjason.minari.network.ApiService
import com.nohjason.minari.network.TokenAuthenticator
import com.nohjason.minari.preferences.PreferencesManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.content.Context
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    private const val BASE_URL = "https://cheongfordo.kr"

    // 1. 동기용 API, PreferencesManager 준비
    private lateinit var preferencesManager: PreferencesManager
    private val syncApiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    fun init(context: Context) {
        preferencesManager = PreferencesManager(context.applicationContext)
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .authenticator(TokenAuthenticator(preferencesManager, syncApiService))
            .build()
    }

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

