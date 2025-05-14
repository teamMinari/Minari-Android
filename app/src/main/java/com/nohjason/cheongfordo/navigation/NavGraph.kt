package com.nohjason.cheongfordo.navigation

import ProfileMAinScreen
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.animation.fadeIn
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
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
import com.nohjason.cheongfordo.screens.auth.viewmodel.LoginViewModel
import com.nohjason.cheongfordo.screens.chat.ChatScreen
import com.nohjason.cheongfordo.screens.home.HomeScreen
import com.nohjason.cheongfordo.screens.term.TermScreen
import com.nohjason.cheongfordo.screens.news.NewsScreen
import com.nohjason.cheongfordo.screens.profile.alias_screen.AliasScreen
import com.nohjason.cheongfordo.screens.profile.directory_screen.DirecScreen
import com.nohjason.cheongfordo.screens.profile.directory_screen.direc_data.DirecViewModel
import com.nohjason.cheongfordo.screens.rout.Grape
import com.nohjason.cheongfordo.screens.rout.Grapes
import com.nohjason.cheongfordo.screens.rout.Rout
import com.nohjason.cheongfordo.screens.quiz.data.QuizViewModel
import com.nohjason.cheongfordo.screens.quiz.quiz_end_screen.QuizEndScreen
import com.nohjason.cheongfordo.screens.quiz.quiz_play.QuizPlayScreen
import com.nohjason.cheongfordo.screens.quiz.quiz_play.SeletO
import com.nohjason.cheongfordo.screens.quiz.quiz_play.SeletX
import com.nohjason.cheongfordo.screens.quiz.quiz_main.QuizMainScreen
import com.nohjason.cheongfordo.screens.search.Search

@SuppressLint("ComposableDestinationInComposeScope")
@Composable
fun NavGraph(
    navController: NavHostController,
//    applicationContext: Context,
//    lifecycleScope: LifecycleCoroutineScope,
    loginViewModel: LoginViewModel,
    quizViewModel: QuizViewModel = hiltViewModel()
) {
    // 토큰 가져오기 (예시, 실제 구현에 맞게 수정)
//    val preferences = getPreferences()
//    val token = getFromPreferences(preferences, "token")

//    val startDestination = if (token.isNullOrEmpty()) {
//        Screens.FirstScreen.rout
//    } else {
//        BottomScreen.Home.rout
//    }

    NavHost(
        navController = navController,
        startDestination = BottomScreen.Home.rout,
        enterTransition = { fadeIn(animationSpec = tween(0)) }
    ) {

        composable(Screens.FirstScreen.rout) {
            FirstScreen(navController = navController)
        }

        composable(Screens.Login.rout) {
            LoginScreen(navController = navController)
        }

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

        composable(Screens.ChatScreen.rout) {
            ChatScreen(naviController = navController)
        }

        composable(BottomScreen.Rout.rout) {
            Rout(navController = navController)
        }

        composable(BottomScreen.News.rout) {
            NewsScreen(navController = navController)
        }

        composable(BottomScreen.Home.rout) {
            HomeScreen(navController = navController)
        }

        composable(BottomScreen.Profile.rout) {
            // 필요시 LaunchedEffect 등으로 프로필 데이터 로드 가능
//            ProfileMAinScreen(
//                navHostController = navController,
//                token = token,
//                loginViewModel = loginViewModel,
//                direcViewModel = DirecViewModel()
//            )
        }

        composable(Screens.Directory.rout) {
            DirecScreen( navController = navController)
        }

        composable(Screens.Alias.rout) {
            AliasScreen(navHostController = navController)
        }

        composable(Screens.Search.rout) {
            Search(navController = navController)
            // 중첩 composable 주의: 필요시 분리 권장
            composable(Screens.Alias.rout) {
                AliasScreen(navHostController = navController)
            }
        }

        composable(Screens.Grapes.rout + "/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 0
            Grapes(id = id, navController = navController)
        }

        composable(Screens.Grape.rout + "/{id}/{title}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 0
            val title = backStackEntry.arguments?.getString("title") ?: ""
            Grape(navController = navController, gpseId = id, title = title)
        }

        composable(Screens.Term.rout + "/{text}") { backStackEntry ->
            val text = backStackEntry.arguments?.getString("text")?.replace("@", "/") ?: ""
            TermScreen(text, navController = navController)
        }

        // 퀴즈 관련 화면들 (애니메이션 포함)
        composable(
            BottomScreen.Quiz.rout
        ) {
            QuizMainScreen(
                navHostController = navController,
                quizViewModel = quizViewModel,
            )
        }

        composable(
            Screens.QuizSelectO.rout,
            enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) }
        ) {
            SeletO(navHostController = navController, quizViewModel = quizViewModel)
        }

        composable(
            Screens.QuizSelectX.rout,
            enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) }
        ) {
            SeletX(navHostController = navController, quizViewModel = quizViewModel)
        }

        composable(
            Screens.QuizPlayScreen.rout,
            enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) }
        ) {
            QuizPlayScreen(navHostController = navController, quizViewModel = quizViewModel)
        }

        composable(
            Screens.QuizEndScreen.rout,
            enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) }
        ) {
            QuizEndScreen(quizViewModel = quizViewModel, navController = navController)
        }

    }
}
