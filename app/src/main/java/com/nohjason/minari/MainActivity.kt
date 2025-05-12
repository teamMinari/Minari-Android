package com.nohjason.minari

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nohjason.minari.preferences.PreferencesManager
import com.nohjason.minari.screens.auth.viewmodel.LoginViewModel
import com.nohjason.minari.ui.theme.MinariTheme
import com.nohjason.myapplication.network.RetrofitInstance
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

//@AndroidEntryPoint
//class MainActivity : ComponentActivity() {
//    private val loginViewModel: LoginViewModel by viewModels()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        //인스턴스 코드
//        RetrofitInstance.init(applicationContext)
//
//        setContent {
//            MinariTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    MainScreen(loginViewModel = loginViewModel)
////                    ScreenSetup(viewModel, loginViewModel)
//                }
//            }
//        }
//    }
//}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    // 필요시 직접 주입 가능
    @Inject
    lateinit var preferencesManager: PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 더 이상 RetrofitInstance.init() 호출하지 않음

        setContent {
            MinariTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(loginViewModel = loginViewModel)
                }
            }
        }
    }
}
