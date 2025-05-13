package com.nohjason.cheongfordo.navigation.bottombar

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.nohjason.cheongfordo.ui.theme.MinariBlue500
import com.nohjason.cheongfordo.ui.theme.MinariGray300
import com.nohjason.cheongfordo.ui.theme.caption_medium

@Composable
fun BottomBar(
    navController: NavHostController,
    navBackStackEntry: NavBackStackEntry?
) {
    val bottomScreens = listOf(
        BottomScreen.Rout,
        BottomScreen.News,
        BottomScreen.Home,
        BottomScreen.Quiz,
        BottomScreen.Profile,
    )

    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        bottomScreens.forEach { screen ->
            AddItem(
                bottomScreen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }

}

@Composable
fun RowScope.AddItem(
    bottomScreen: BottomScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottomNavigationItem(
        label = {
            val Color = if (bottomScreen.rout == currentDestination?.route) MinariBlue500 else MinariGray300
            Text(
                text = bottomScreen.title,
                style = caption_medium,
                color = Color
            )
        },
        icon = {
            val iconColor = if (bottomScreen.rout == currentDestination?.route) MinariBlue500 else MinariGray300
            Icon(
                imageVector = bottomScreen.icon(),
                contentDescription = null,
                tint = iconColor
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == bottomScreen.rout
        } == true,
        onClick = {
            Log.d("TAG", "AddItem: ${bottomScreen.rout}")
            navController.navigate(bottomScreen.rout)
        },
        selectedContentColor = MinariGray300,
        unselectedContentColor = Color.LightGray,
        modifier = Modifier.background(Color.White)
    )
}