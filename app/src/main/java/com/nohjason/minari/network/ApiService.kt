package com.nohjason.minari.network

import com.nohjason.minari.network.response.AddDeleteTerm
import com.nohjason.minari.network.response.BookResponse
import com.nohjason.minari.network.response.GetAllLikesTerm
import com.nohjason.minari.network.response.GetTerm
import com.nohjason.minari.network.response.Quize
import com.nohjason.minari.network.response.rout.Grape
import com.nohjason.minari.network.response.rout.GrapeSeed
import com.nohjason.minari.network.response.rout.Grapes
import com.nohjason.minari.network.response.rout.GrapesAll
import com.nohjason.minari.screens.login.response.LoginRequest
import com.nohjason.minari.screens.login.response.LoginResponse
import com.nohjason.minari.screens.login.response.RegisterRequest
import com.nohjason.minari.screens.login.response.RegisterResponse
import com.nohjason.minari.screens.profile.directory_screen.direc_data.DirecGpResponse
import com.nohjason.minari.screens.profile.directory_screen.direc_data.DirecGpsResponse
import com.nohjason.minari.screens.profile.directory_screen.direc_data.DirecGpseResponse
import com.nohjason.minari.screens.profile.directory_screen.direc_data.DirecTermResponse
import com.nohjason.minari.screens.rout.response.GetAllNews
import com.nohjason.minari.screens.rout.response.LikesResponse
import com.nohjason.minari.screens.profile.profile_data.ProfileResponse
import com.nohjason.minari.screens.quiz.data.QuestionResponse
import com.nohjason.myapplication.network.response.Term
import com.nohjason.myapplication.network.response.TermResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/terms/all")
    suspend fun getTerms(
        @Header("Authorization") token: String
    ): List<TermResponse>

    @GET("/terms")
    suspend fun getOneTerm(
        @Query("termNm") termNm: String,
        @Header("Authorization") token: String,
    ): Term

    @PATCH("/likes/toggle")
    suspend fun addDeleteTerm(
        @Header("Authorization") token: String,
        @Query("word") word: String,
    ): AddDeleteTerm

    @GET("/likes/my")
    suspend fun getBookTerms(
        @Header("Authorization") token: String
    ): BookResponse

    // login
    @POST("/member/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>

    @POST("/member/register")
    suspend fun register(
        @Body registerResponse: RegisterRequest
    ): RegisterResponse

    // 포도송이 전체 조회
    @GET("/gps")
    suspend fun getAllGps(
        @Header("Authorization") token: String
    ): Response<GrapesAll>

    // 포도알 조회
    @GET("/gps/{gpsId}")
    suspend fun getGps(
        @Header("Authorization") token: String,
        @Path("gpsId") gpsId: Int,
    ): Response<Grapes>

    // 포도씨 전체 조회
    @GET("/gp/{gpId}")
    suspend fun getAllGrape(
        @Header("Authorization") token: String,
        @Path("gpId") gpId: Int,
    ): Response<Grape>

    // 포도씨 조회
    @GET("/gpse/{gpseId}")
    suspend fun getGpse(
        @Header("Authorization") token: String,
        @Path("gpseId") gpseId: Int,
    ): Response<GrapeSeed>

    @GET("/questions/{questionIdx}")
    suspend fun getQuize(
        @Header("Authorization") token: String,
        @Path("questionIdx") questionIdx: Int,
    ): Response<Quize>

    // 좋아요
    @PATCH("/likes")
    suspend fun likes(
        @Header("Authorization") token: String,
        @Query("category") category: String,
        @Query("id") id: Int,
    ): Response<LikesResponse>

    // 뉴스 가져오기
    @GET("/news")
    suspend fun getAllNews(
        @Header("Authorization") token: String,
        @Query("category") category: String,
    ): Response<GetAllNews>

    // 단어 용어 가져오기
    @GET("/terms/name/{termNm}")
    suspend fun getTerm(
        @Header("Authorization") token: String,
        @Path("termNm") termNm: String
    ): Response<GetTerm>

    // 용어 좋아요 전체 가져오기
    @GET("/likes/term")
    suspend fun getAllLikesTerm(
        @Header("Authorization") token: String,
    ): Response<GetAllLikesTerm>

    //프로필 정보
    @GET("/member/profile")
    suspend fun getProfile(
        @Header("Authorization") token: String
    ): Response<ProfileResponse>

    //퀴즈 문제
    @GET("/questions/level/{level}")
    suspend fun getQuestion(
        @Header("Authorization") token: String,
        @Path("level") level: Int
    ): QuestionResponse

    //저장목록
    @GET("/likes/term")
    suspend fun getDiercTerm(
        @Header("Authorization") token: String
    ): DirecTermResponse

    @GET("/likes/gpse")
    suspend fun getDiercGpse(
        @Header("Authorization") token: String
    ): DirecGpseResponse

    @GET("/likes/gps")
    suspend fun getDiercGps(
        @Header("Authorization") token: String
    ): DirecGpsResponse

    @GET("/likes/gp")
    suspend fun getDierctGp(
        @Header("Authorization") token: String
    ): DirecGpResponse
}

// test