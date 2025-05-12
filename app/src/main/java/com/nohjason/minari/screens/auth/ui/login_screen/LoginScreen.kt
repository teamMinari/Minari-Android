package com.nohjason.minari.screens.auth.ui.login_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.nohjason.minari.R
import com.nohjason.minari.navigation.Screens
import com.nohjason.minari.navigation.bottombar.BottomScreen
import com.nohjason.minari.preferences.PreferencesManager
import com.nohjason.minari.screens.auth.viewmodel.LoginViewModel
import com.nohjason.minari.screens.ui.button.MinariButton
import com.nohjason.minari.screens.ui.text.MinariInputField
import com.nohjason.minari.screens.ui.text.MinariText
import com.nohjason.minari.ui.theme.MinariBlue
import com.nohjason.minari.ui.theme.MinariBlue500
import com.nohjason.minari.ui.theme.MinariGray200
import com.nohjason.minari.ui.theme.MinariGray500
import com.nohjason.minari.ui.theme.MinariGray900
import com.nohjason.minari.ui.theme.MinariWhite
import com.nohjason.minari.ui.theme.b2_bold
import com.nohjason.minari.ui.theme.button_bold
import com.nohjason.minari.ui.theme.button_medium
import com.nohjason.minari.ui.theme.rixfont


@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = viewModel()
) {
    val context = LocalContext.current
    val preferencesManager = remember { PreferencesManager(context) }
    val loginResponse by loginViewModel.loginRequest.collectAsState()

    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var saveLogin by rememberSaveable { mutableStateOf(false) }

    val isButtonEnabled = id.isNotEmpty() && password.isNotEmpty()

    // 화면 크기 가져오기
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    // 비율 기반 사이즈 계산 함수
    fun heightRatio(ratio: Float) = screenHeight * ratio
    fun widthRatio(ratio: Float) = screenWidth * ratio

    LaunchedEffect(loginResponse) {
        if (loginResponse != null) {
            preferencesManager.saveToken(loginResponse!!.data.accessToken)
            if (saveLogin) {
                preferencesManager.saveRefreshToken(loginResponse!!.data.refreshToken)
                preferencesManager.setAutoLogin(true)
            } else {
                preferencesManager.saveRefreshToken("")
                preferencesManager.setAutoLogin(false)
            }
            navController.navigate(BottomScreen.Home.rout) { popUpTo(0) }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(color = MinariWhite)
            .fillMaxSize()
            .padding(horizontal = widthRatio(0.06f)) // 약 24dp 기준
    ) {
        Spacer(modifier = Modifier.height(heightRatio(0.18f))) // 약 148dp 기준

        MinariText(
            text = "청포도",
            color = MinariBlue,
            fontFamily = rixfont,
            size = 32
        )

        Spacer(modifier = Modifier.height(heightRatio(0.04f))) // 약 32dp 기준

        MinariInputField(
            icon = null,
            label = "아이디 입력",
            onValueChange = { id = it }
        )

        Spacer(modifier = Modifier.height(heightRatio(0.015f))) // 약 12dp 기준

        MinariInputField(
            icon = painterResource(
                id = if (passwordVisible) R.drawable.ic_visibility else R.drawable.ic_visibility_off
            ),
            label = "비밀번호 입력",
            onValueChange = { password = it },
            onClickAction = { passwordVisible = !passwordVisible },
            isPassword = !passwordVisible
        )

        Spacer(modifier = Modifier.height(heightRatio(0.015f)))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(
                    id = if (saveLogin) R.drawable.ic_check_on else R.drawable.ic_check_off
                ),
                contentDescription = "로그인 저장 체크",
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(widthRatio(0.055f)) // 약 20dp
                    .clickable { saveLogin = !saveLogin }
            )
            Spacer(modifier = Modifier.width(widthRatio(0.01f)))
            Text(
                text = "로그인 저장",
                style = button_medium,
                color = Color(0xFF171717),
                modifier = Modifier.clickable { saveLogin = !saveLogin }
            )
        }

        Spacer(modifier = Modifier.height(heightRatio(0.1f))) // 약 80dp

        MinariButton(
            text = "로그인",
            size = "Large",
            textColor = MinariWhite,
            buttonColor = MinariBlue500,
            line = false,
            enabled = isButtonEnabled,
            modifier = Modifier.fillMaxWidth()
        ) {
            loginViewModel.login(id = id, password = password)
        }

        Spacer(modifier = Modifier.height(heightRatio(0.015f)))

        MinariButton(
            text = "구글로 계속하기",
            size = "Large",
            textColor = MinariGray500,
            buttonColor = MinariWhite,
            line = true,
            enabled = isButtonEnabled,
            modifier = Modifier.fillMaxWidth(),
            icon = painterResource(R.drawable.ic_google)
        ) {
            Toast.makeText(context, "아직 구현중인 기능입니다.", Toast.LENGTH_SHORT).show()
        }

        Spacer(modifier = Modifier.height(heightRatio(0.025f)))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "아직 계정이 없다면?",
                style = button_medium,
                color = MinariGray500
            )
            Spacer(modifier = Modifier.width(widthRatio(0.01f)))
            Text(
                text = "회원가입",
                style = button_bold,
                color = MinariGray900,
                modifier = Modifier.clickable {
                    navController.navigate(Screens.IdScreen.rout)
                }
            )
        }
    }
}

//@Preview(showBackground = true, widthDp = 360, heightDp = 720)
//@Composable
//fun LoginScreenPreview() {
//    val context = LocalContext.current
//    val navController = NavController(context)
//    LoginScreen(navController = navController)
//}