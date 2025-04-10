package com.nohjason.minari.screens.auth.ui.register_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nohjason.minari.R
import com.nohjason.minari.navigation.Screens
import com.nohjason.minari.screens.auth.ui.keyboardAsState
import com.nohjason.minari.screens.auth.viewmodel.RegisterViewModel
import com.nohjason.minari.screens.ui.button.MinariButton
import com.nohjason.minari.screens.ui.text.MinariInputField
import com.nohjason.minari.ui.theme.MinariBlue500
import com.nohjason.minari.ui.theme.MinariWhite
import com.nohjason.minari.ui.theme.h4_bold
import com.nohjason.minari.ui.theme.b2_medium


@Composable
fun PasswordScreen(
    navController: NavController,
    registerViewModel: RegisterViewModel = hiltViewModel()
) {
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isButtonEnabled by remember { mutableStateOf(false) }

    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current
    val isKeyboardVisible by keyboardAsState()
    var showPasswordMismatch by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
        horizontalAlignment = Alignment.Start
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "뒤로가기",
                modifier = Modifier
                    .padding(top = 17.dp)
                    .clickable { focusManager.clearFocus() } // 포커스 해제
            )

            Spacer(modifier = Modifier.height(44.dp))

            Text(
                text = "로그인 시 사용될 비밀번호를 \n입력해 주세요!",
                style = h4_bold
            )

            Spacer(modifier = Modifier.height(76.dp))

            //비번 입력
            MinariInputField(
                icon = painterResource(
                    id = if (passwordVisible)
                        R.drawable.ic_visibility else R.drawable.ic_visibility_off
                ),
                label = "비밀번호 입력",
                onValueChange = {
                    password = it
                    isButtonEnabled = password.isNotEmpty() && confirmPassword.isNotEmpty()
                },
                onClickAction = {
                    passwordVisible = !passwordVisible
                },
                isPassword = !passwordVisible
            )

            Spacer(modifier = Modifier.height(16.dp))

            //비번 확인 필드
            MinariInputField(
                icon = painterResource(
                    id = if (confirmPasswordVisible)
                        R.drawable.ic_visibility else R.drawable.ic_visibility_off
                ),
                label = "비밀번호 확인",
                onValueChange = {
                    confirmPassword = it
                    isButtonEnabled = password.isNotEmpty() && confirmPassword.isNotEmpty()
                },
                onClickAction = {
                    confirmPasswordVisible = !confirmPasswordVisible 
                },
                isPassword = !confirmPasswordVisible
            )

            if (showPasswordMismatch) {
                Text(
                    text = "비밀번호가 일치하지 않습니다.",
                    color = Color.Red,
                    style = b2_medium
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .imePadding(),
            contentAlignment = Alignment.Center
        ) {
            MinariButton(
                text = "다음",
                size = "Large",
                textColor = MinariWhite,
                buttonColor = MinariBlue500,
                line = false,
                enabled = isButtonEnabled,
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (password != confirmPassword) {
                        showPasswordMismatch = true
                        return@MinariButton
                    }
                    showPasswordMismatch = false
                    registerViewModel.updatePassword(newPassword = password)
                    registerViewModel.updateConfirmPassword(newConfirmPassword = password)
                    navController.navigate(Screens.EmailScreen.rout)

                    focusManager.clearFocus()
                }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}









//@Preview(showBackground = true)
//@Composable
//fun PrePasswordScreen(){
//    Column(
//        modifier = Modifier
//            .background(Color.White)
//            .fillMaxSize()
//    ){
//        PasswordScreen()
//    }
//}