package com.nohjason.minari

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nohjason.cheongfordo.navigation.bottombar.BottomBar
import com.nohjason.cheongfordo.navigation.NavGraph
import com.nohjason.cheongfordo.navigation.Screens
import com.nohjason.cheongfordo.screens.auth.viewmodel.LoginViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    loginViewModel: LoginViewModel,
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val hideBottomBarRoutes = listOf(
        Screens.QuizSelectX.rout,
        Screens.QuizSelectO.rout,
        Screens.QuizPlayScreen.rout,
        Screens.Login.rout,
        Screens.IdScreen.rout,
        Screens.FirstScreen.rout,
        Screens.PasswordScreen.rout,
        Screens.EmailScreen.rout,
        Screens.LikeScreen.rout,
        Screens.SelectJobScreen.rout,
        Screens.ChatScreen.rout,
    )

    val showBottomBar = currentRoute !in hideBottomBarRoutes

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomBar(
                    navController = navController,
                    navBackStackEntry = navBackStackEntry
                )
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            NavGraph(
                navController = navController,
                loginViewModel = loginViewModel,
            )
        }
    }
}