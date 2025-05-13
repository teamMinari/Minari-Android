package com.nohjason.cheongfordo.navigation.bottombar

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.nohjason.cheongfordo.R

sealed class BottomScreen(
    val rout: String,
    val title: String,
    val icon: @Composable () -> ImageVector
) {
    data object Home : BottomScreen(
        rout = "home",
        title = "홈",
        icon = { ImageVector.vectorResource(id = R.drawable.ic_home) }
    )

    data object Profile : BottomScreen(
        rout = "profile",
        title = "마이",
        icon = { ImageVector.vectorResource(id = R.drawable.ic_profile) }
    )

    data object News : BottomScreen(
        rout = "news",
        title = "뉴스",
        icon = { ImageVector.vectorResource(id = R.drawable.ic_news) }
    )

    data object Quiz : BottomScreen(
        rout = "quiz",
        title = "퀴즈",
        icon = { ImageVector.vectorResource(id = R.drawable.ic_ticket) }
    )

    data object Rout : BottomScreen(
        rout = "rout",
        title = "튜토리얼",
        icon = { ImageVector.vectorResource(id = R.drawable.ic_map) }
    )
}