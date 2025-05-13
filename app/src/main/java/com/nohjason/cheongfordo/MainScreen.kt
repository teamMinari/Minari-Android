package com.nohjason.cheongfordo

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
    var showBottomBar by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = navController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            showBottomBar = when (destination.route) {
                Screens.QuizSelectX.rout -> false
                Screens.QuizSelectO.rout -> false
                Screens.QuizPlayScreen.rout -> false
                Screens.Login.rout -> false
                Screens.IdScreen.rout -> false
                Screens.FirstScreen.rout -> false
                Screens.PasswordScreen.rout -> false
                Screens.EmailScreen.rout -> false
                Screens.LikeScreen.rout -> false
                Screens.SelectJobScreen.rout -> false
                Screens.ChatScreen.rout -> false
                else -> true
            }
        }
    }

    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            if (currentDestination?.route !in listOf(
                    Screens.QuizSelectX.rout,
                    Screens.QuizSelectO.rout,
                    Screens.QuizPlayScreen.rout ,
                    Screens.Login.rout ,
                    Screens.IdScreen.rout ,
                    Screens.FirstScreen.rout ,
                    Screens.PasswordScreen.rout ,
                    Screens.EmailScreen.rout ,
                    Screens.LikeScreen.rout ,
                    Screens.SelectJobScreen.rout,
                    Screens.ChatScreen.rout ,
                )
            ) {
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
            )
        }
    }
}