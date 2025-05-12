package com.nohjason.myapplication.network

import com.nohjason.minari.network.ApiService
import com.nohjason.minari.network.TokenAuthenticator
import com.nohjason.minari.preferences.PreferencesManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.content.Context
import com.google.firebase.inject.Provider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Authenticator
import okhttp3.Route
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Qualifier
import javax.inject.Singleton


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthApiService

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://cheongfordo.kr"

    @Provides
    @Singleton
    fun providePreferencesManager(@ApplicationContext context: Context): PreferencesManager =
        PreferencesManager(context)

    @AuthApiService
    @Provides
    @Singleton
    fun provideAuthApiService(): ApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideTokenAuthenticator(
        preferencesManager: PreferencesManager,
        @AuthApiService apiService: ApiService  // AuthApiService로 ApiService 주입
    ): TokenAuthenticator = TokenAuthenticator(preferencesManager, apiService)

    @Provides
    @Singleton
    fun provideOkHttpClient(
        tokenAuthenticator: TokenAuthenticator
    ): OkHttpClient = OkHttpClient.Builder()
        .authenticator(tokenAuthenticator)
        // 필요에 따라 타임아웃, 로깅 인터셉터 등 추가 가능
        .build()

    @Provides
    @Singleton
    fun provideApiService(okHttpClient: OkHttpClient): ApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
}


