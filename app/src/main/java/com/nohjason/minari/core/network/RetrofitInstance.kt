package com.nohjason.myapplication.network

import com.nohjason.minari.network.ApiService
import com.nohjason.minari.network.TokenAuthenticator
import com.nohjason.minari.preferences.PreferencesManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

//object RetrofitInstance {
//    private const val BASE_URL = "https://cheongfordo.kr"
//
//    // 1. 동기용 API, PreferencesManager 준비
//    private lateinit var preferencesManager: PreferencesManager
//    private val syncApiService: ApiService by lazy {
//        Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(ApiService::class.java)
//    }
//
//    fun init(context: Context) {
//        preferencesManager = PreferencesManager(context.applicationContext)
//    }
//
//    private val okHttpClient: OkHttpClient by lazy {
//        OkHttpClient.Builder()
//            .authenticator(TokenAuthenticator(preferencesManager, syncApiService))
//            .build()
//    }
//
//    val api: ApiService by lazy {
//        Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(okHttpClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(ApiService::class.java)
//    }
//}

@Module
@InstallIn(SingletonComponent::class)
object RetrofitInstance {

    private const val BASE_URL = "https://cheongfordo.kr"

    @Provides
    @Singleton
    fun providePreferencesManager(@ApplicationContext context: Context): PreferencesManager {
        return PreferencesManager(context)
    }

    // OkHttpClient는 ApiService 의존성 제거, TokenAuthenticator는 PreferencesManager만 받음
    @Provides
    @Singleton
    fun provideOkHttpClient(
        preferencesManager: PreferencesManager,
        tokenAuthenticator: TokenAuthenticator
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .authenticator(tokenAuthenticator)
            .build()
    }

    // ApiService는 OkHttpClient를 주입받아 생성
    @Provides
    @Singleton
    fun provideApiService(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    // TokenAuthenticator를 별도로 @Provides 하거나 @Inject 생성자로 관리
    @Provides
    @Singleton
    fun provideTokenAuthenticator(
        preferencesManager: PreferencesManager,
        apiService: ApiService
    ): TokenAuthenticator {
        return TokenAuthenticator(preferencesManager, apiService)
    }
}


