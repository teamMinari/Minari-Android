package com.nohjason.minari

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
import com.nohjason.minari.navigation.bottombar.BottomBar
import com.nohjason.minari.navigation.NavGraph
import com.nohjason.minari.navigation.Screens
import com.nohjason.minari.screens.auth.viewmodel.LoginViewModel
import com.nohjason.minari.screens.quiz.data.QuizViewModel

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
                    Screens.FirstScreen.rout,
                    Screens.Login.rout,
                    Screens.Signup.rout,
                    Screens.Question.rout,
                    Screens.LastSignup.rout,
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