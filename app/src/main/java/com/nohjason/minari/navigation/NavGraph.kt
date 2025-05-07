package com.nohjason.minari.navigation

import ProfileMAinScreen
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nohjason.minari.navigation.bottombar.BottomScreen
import com.nohjason.minari.screens.auth.ui.FirstScreen
import com.nohjason.minari.screens.auth.ui.login_screen.LoginScreen
import com.nohjason.minari.screens.auth.ui.register_screen.EmailScreen
import com.nohjason.minari.screens.auth.ui.register_screen.IdScreen
import com.nohjason.minari.screens.auth.ui.register_screen.LikeScreen
import com.nohjason.minari.screens.auth.ui.register_screen.PasswordScreen
import com.nohjason.minari.screens.auth.ui.register_screen.SelectJobScreen
import com.nohjason.minari.screens.auth.viewmodel.LoginViewModel
import com.nohjason.minari.screens.home.HomeScreen
import com.nohjason.minari.screens.term.TermScreen
import com.nohjason.minari.screens.news.News
import com.nohjason.minari.screens.profile.alias_screen.AliasScreen
import com.nohjason.minari.screens.profile.directory_screen.DirecScreen
import com.nohjason.minari.screens.profile.directory_screen.direc_data.DirecViewModel
import com.nohjason.minari.screens.profile.profile_data.DummyProfileData.profileData
import com.nohjason.minari.screens.profile.profile_data.ProfileViewModel
import com.nohjason.minari.screens.rout.Grape
import com.nohjason.minari.screens.rout.Grapes
import com.nohjason.minari.screens.rout.Rout
import com.nohjason.minari.screens.quiz.data.QuizViewModel
import com.nohjason.minari.screens.quiz.quiz_end_screen.QuizEndScreen
import com.nohjason.minari.screens.quiz.quiz_play.QuizPlayScreen
import com.nohjason.minari.screens.quiz.quiz_play.SeletO
import com.nohjason.minari.screens.quiz.quiz_play.SeletX
import com.nohjason.minari.screens.quiz.quiz_main.QuizMainScreen

@SuppressLint("ComposableDestinationInComposeScope")
@Composable
fun NavGraph(
    navController: NavHostController,
) {
//    val preferences = getPreferences()
//    val token = getFromPreferences(preferences, "token")
    val context = LocalContext.current
//    val data = profileViewModel.profileData.collectAsState().value

    NavHost(
        navController = navController,
        startDestination = BottomScreen.Home.rout,
    ) {

        composable(Screens.FirstScreen.rout) {
            FirstScreen(navController = navController)
        }

        //singup
        composable(Screens.IdScreen.rout) {
            IdScreen(navController = navController)
        }
        composable(Screens.PasswordScreen.rout) {
            PasswordScreen(navController = navController)
        }
        composable(Screens.EmailScreen.rout) {
            EmailScreen(navController = navController)
        }
        composable(Screens.LikeScreen.rout) {
            LikeScreen(navController = navController)
        }
        composable(Screens.SelectJobScreen.rout) {
            SelectJobScreen(navController = navController)
        }

        //login
        composable(Screens.Login.rout) {
            LoginScreen(navController = navController)
        }

        // 튜토리얼
        composable(BottomScreen.Rout.rout) {
            Rout(navController = navController)
        }

        // 뉴스
        composable(BottomScreen.News.rout) {
            News(navController = navController)
        }

        // 홈
        composable(BottomScreen.Home.rout) {
            HomeScreen(
                navController = navController,
            )
        }

        // 퀴즈
        composable(BottomScreen.Quiz.rout) {
//            QuizMainScreen(navHostController = navController, quizViewModel = quizViewModel, token=token)
        }

        // 프로필
        composable(BottomScreen.Profile.rout) {
            LaunchedEffect(Unit) {
//                profileViewModel.getProfile(token)
            }
            ProfileMAinScreen(navHostController = navController, profileData = profileData)
        }

        //저장목록
        composable(Screens.Directory.rout) {
            DirecScreen(
//                direcViewModel = DirecViewModel,
//                token = token
            )
        }

        //칭호
        composable(Screens.Alias.rout){
            AliasScreen(level = profileData.level, exp = profileData.exp, navController = navController)
        }

        // 포도알
        composable(Screens.Grapes.rout + "/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: "0"
            Grapes(
                id = id.toInt(),
                navController = navController,
            )
        }

        // 포도씨
        composable(Screens.Grape.rout + "/{id}/{title}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: "0"
            val title = backStackEntry.arguments?.getString("title") ?: ""
            Grape(
                navController = navController,
                gpseId = id.toInt(),
                title = title,
            )
        }

        // 용어
        composable(Screens.Term.rout + "/{text}") { backStackEntry ->
            val text = backStackEntry.arguments?.getString("text") ?: ""
            TermScreen(text, navController = navController)
        }


        // 로그인
        composable(
            route = Screens.Signup.rout,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(700)
                )
            }
        ) {
//            SelfSignUpScreen(
//                navController = navController
//            )
        }



        //퀴즈
        composable(
            "quizplay",
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            }
        ) {
//            SeletO(navHostController = navController, quizViewModel = quizViewModel)
        }
        composable(
            Screens.QuizSelectX.rout,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            }
        ) {
//            SeletX(navHostController = navController, quizViewModel = quizViewModel)
        }
        composable(
            Screens.QuizPlaycreen.rout,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            }
        ) {
//            QuizPlayScreen(navHostController = navController, quizViewModel = quizViewModel)
        }
        composable(
            Screens.QuizEndScreen.rout,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            }
        ) {
//            QuizEndScreen(quizViewModel = quizViewModel, navController = navController)
        }




        //모르는거
        composable(
            route = Screens.Question.rout,
        ) {

        }
    }
}