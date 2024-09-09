package com.nohjason.minari.screens.profile.likes

class LikeData {
}

data class ApiResponse(
    val status: Int,
    val message: String,
    val data: List<Term>
)

// Term 데이터 클래스
data class Term(
    val termId: Int,
    val termNm: String,
    val termExplain: String,
    val termDifficulty: String
)

data class GPSItem(
    val gpseId: Int,
    val gpseName: String,
    val gpseTime: Int,
    val gpseLike: Boolean
)

data class Gps(
    val gpsId: Int,
    val gpsContent: String,
    val gpsImg: String,
    val gpsLike: Boolean,
    val gpsTpList: List<String>
)

data class Gp(
    val gpId: Int,
    val gpName: String,
    val gpImg: String,
    val gpLike: Boolean
)