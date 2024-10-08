package com.nohjason.minari.screens.login.screen.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nohjason.minari.R
import com.nohjason.minari.navigation.bottombar.BottomScreen
import com.nohjason.minari.preferences.getPreferences
import com.nohjason.minari.preferences.saveToPreferences
import com.nohjason.minari.screens.login.LoginTextField
import com.nohjason.minari.screens.login.LoginViewModel
import com.nohjason.minari.ui.theme.MinariBlue
import com.nohjason.minari.ui.theme.poppins_regular
import com.nohjason.minari.ui.theme.poppins_semibold
import com.nohjason.minari.ui.theme.pretendard_bold

@Composable
fun SelfLoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 25.dp, vertical = 14.dp)
            .verticalScroll(scrollState),
    ) {
        Image(
            painterResource(id = R.drawable.grape),
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )

        Spacer(modifier = Modifier.weight(0.1f))

        Text(
            text = "로그인",
            fontFamily = poppins_semibold,
            fontSize = 25.sp
        )
        Text(
            text = "아직 회원가입을 하지 않았다면",
            fontSize = 15.sp,
            fontFamily = poppins_regular
        )
        Text(
            text = "회원가입",
            color = MinariBlue,
            fontSize = 15.sp,
            fontFamily = poppins_semibold,
            modifier = Modifier
                .clickable {
                    navController.navigate("Signup")
                },
        )

        Spacer(modifier = Modifier.weight(0.1f))

        var id by remember { mutableStateOf("adminacc") }
        LoginTextField(
            modifier = Modifier,
            value = id,
            icon_name = "아이디",
            text = "아이디을 입력하세요",
            onValueChange = { id = it },
            visibility = true
        ) {

        }

        Spacer(modifier = Modifier.weight(0.1f))

        var password by rememberSaveable { mutableStateOf("rlaghwns00!!") }
        LoginTextField(
            modifier = Modifier,
            value = password,
            icon_name = "비밀번호",
            text = "비밀번호를 입력하세요",
            onValueChange = { password = it },
            visibility = false
        ) {

        }

        var check by rememberSaveable { mutableStateOf(false) }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = check, onCheckedChange = { check = it })
            Text(text = "로그인 저장")
            Spacer(modifier = Modifier.weight(0.1f))
            Text(
                text = "비밀번호를 잊으셨나요?",
                modifier = Modifier.clickable {  }
            )
        }

        Spacer(modifier = Modifier.weight(0.1f))

        val preferences = getPreferences()
        val loginResponse by loginViewModel.loginRequest.collectAsState()
        LaunchedEffect(loginResponse) {
            if (loginResponse != null ) {
                saveToPreferences(preferences, "token", loginResponse!!.data.accessToken)
                navController.navigate(BottomScreen.Home.rout)
                Log.d("TAG", "SelfLoginScreen: ${loginResponse!!.data.accessToken}")
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(CircleShape)
                .background(MinariBlue)
                .clickable {
                    loginViewModel.login(id = id, password = password)
                }
        ) {
            Text(
                text = "로그인",
                color = Color.White,
                fontFamily = pretendard_bold,
                modifier = Modifier
                    .padding(13.dp)
                    .align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.weight(0.6f))
        Spacer(modifier = Modifier.weight(0.6f))
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreLogin() {
    SelfLoginScreen(navController = rememberNavController(), loginViewModel = viewModel())
}