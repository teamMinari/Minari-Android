package com.nohjason.cheongfordo.navigation

sealed class Screens(
    val rout: String,
    val title: String,
) {
    data object ChatScreen : Screens(
        rout = "chatScreen",
        title = "chat",
    )

    data object FirstScreen : Screens(
        rout = "firstScreen",
        title = "First",
    )

    data object Login : Screens(
        rout = "login",
        title = "Login",
    )

    data object Signup : Screens(
        rout = "signup",
        title = "Signup",
    )

    object IdScreen : Screens(
        rout = "id_screen",
        title = "id_screen"
    )

    object PasswordScreen : Screens(
        rout = "password_screen",
        title ="password_screen"
    )

    object EmailScreen : Screens(
        rout = "email_screen",
        title ="email_screen"
    )

    object LikeScreen : Screens(
        rout = "like_screen",
        title = "like_screen"
    )

    object SelectJobScreen : Screens(
        rout = "select_job_screen",
        title = "select_job_screen"
    )


    data object Question : Screens(
        rout = "question",
        title = "Question",
    )

    data object LastSignup : Screens(
        rout = "lastsignup",
        title = "LastSignup",
    )

    data object Grape : Screens(
        rout = "grape",
        title = "Grape",
    )
    data object Grapes : Screens(
        rout = "grapes",
        title = "Grapes",
    )

    data object Term : Screens(
        rout = "term",
        title = "Term",
    )

    //퀴즈
    data object QuizPlayScreen : Screens(
        rout = "quizplay",
        title = "quizplay",
    )
    data object QuizEndScreen : Screens(
        rout = "quizend",
        title = "QuizEnd",
    )
    data object QuizSelectO : Screens(
        rout = "Select_O",
        title = "Select_O",
    )
    data object QuizSelectX : Screens(
        rout = "Select_X",
        title = "Select_X",
    )

    //저장목록
    data object Directory: Screens(
        rout = "myDirectory",
        title = "Directory"
    )

    //칭호
    data object Alias: Screens(
        rout = "myAlias",
        title = "Alias"
    )
}