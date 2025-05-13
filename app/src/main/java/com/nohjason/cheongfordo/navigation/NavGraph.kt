package com.nohjason.cheongfordo.navigation

import ProfileMAinScreen
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nohjason.cheongfordo.navigation.bottombar.BottomScreen
import com.nohjason.cheongfordo.screens.auth.ui.FirstScreen
import com.nohjason.cheongfordo.screens.auth.ui.login_screen.LoginScreen
import com.nohjason.cheongfordo.screens.auth.ui.register_screen.EmailScreen
import com.nohjason.cheongfordo.screens.auth.ui.register_screen.IdScreen
import com.nohjason.cheongfordo.screens.auth.ui.register_screen.LikeScreen
import com.nohjason.cheongfordo.screens.auth.ui.register_screen.PasswordScreen
import com.nohjason.cheongfordo.screens.auth.ui.register_screen.SelectJobScreen
import com.nohjason.cheongfordo.screens.chat.ChatScreen
import com.nohjason.cheongfordo.screens.home.HomeScreen
import com.nohjason.cheongfordo.screens.term.TermScreen
import com.nohjason.cheongfordo.screens.news.NewsScreen
import com.nohjason.cheongfordo.screens.profile.alias_screen.AliasScreen
import com.nohjason.cheongfordo.screens.profile.directory_screen.DirecScreen
import com.nohjason.cheongfordo.screens.profile.profile_data.DummyProfileData.profileData
import com.nohjason.cheongfordo.screens.rout.Grape
import com.nohjason.cheongfordo.screens.rout.Grapes
import com.nohjason.cheongfordo.screens.rout.Rout
import com.nohjason.cheongfordo.screens.quiz.data.QuizViewModel
import com.nohjason.cheongfordo.screens.quiz.quiz_end_screen.QuizEndScreen
import com.nohjason.cheongfordo.screens.quiz.quiz_play.QuizPlayScreen
import com.nohjason.cheongfordo.screens.quiz.quiz_play.SeletO
import com.nohjason.cheongfordo.screens.quiz.quiz_play.SeletX
import com.nohjason.cheongfordo.screens.quiz.quiz_main.QuizMainScreen

@SuppressLint("ComposableDestinationInComposeScope")
@Composable
fun NavGraph(
    navController: NavHostController,
) {
    val quizViewModel: QuizViewModel = hiltViewModel()
//    val preferences = getPreferences()
//    val token = getFromPreferences(preferences, "token")
    val context = LocalContext.current
//    val data = profileViewModel.profileData.collectAsState().value

    NavHost(
        navController = navController,
        startDestination = BottomScreen.Home.rout,
    ) {

        composable(Screens.ChatScreen.rout) {
            ChatScreen(naviController = navController)
        }

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
            NewsScreen(navController = navController)
        }

        // 홈
        composable(BottomScreen.Home.rout) {
            HomeScreen(
                navController = navController,
            )
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
            DirecScreen()
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





        // 퀴즈
        composable(BottomScreen.Quiz.rout) {
            QuizMainScreen(
                navHostController = navController,
                quizViewModel = quizViewModel
            )
        }

        // 퀴즈 엔딩 화면
        composable(
            Screens.QuizPlayScreen.rout,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            }
        ) { backStackEntry ->
            QuizEndScreen(quizViewModel = quizViewModel, navController = navController)
        }

// 퀴즈 플레이 화면
        composable(
            Screens.QuizPlayScreen.rout,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            }
        ) { backStackEntry ->
            QuizPlayScreen(navHostController = navController, quizViewModel = quizViewModel)
        }

// 퀴즈 선택 X 화면
        composable(
            Screens.QuizSelectX.rout,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            }
        ) { backStackEntry ->
            SeletX(navHostController = navController, quizViewModel = quizViewModel)
        }

// 퀴즈 선택 O 화면
        composable(
            Screens.QuizSelectO.rout, // 기존에 "quizplay"로 되어있는데, 필요하면 Screens.QuizSelectO.rout 등으로 변경
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            }
        ) { backStackEntry ->
            SeletO(navHostController = navController, quizViewModel = quizViewModel)
        }





        //모르는거
        composable(
            route = Screens.Question.rout,
        ) {

        }
    }
}