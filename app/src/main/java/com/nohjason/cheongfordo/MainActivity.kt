package com.nohjason.cheongfordo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.nohjason.cheongfordo.screens.auth.viewmodel.LoginViewModel
import com.nohjason.cheongfordo.ui.theme.MinariTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //인스턴스 코드
//        RetrofitInstance.init(applicationContext)

        setContent {
            MinariTheme (
                darkTheme = false,
                dynamicColor = false
            ){
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
//                    containerColor = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(
                        loginViewModel = loginViewModel,
                    )
//                    ScreenSetup(viewModel, loginViewModel)
                }
            }
        }
    }
}