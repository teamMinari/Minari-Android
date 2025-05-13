package com.nohjason.cheongfordo.screens.auth.ui.register_screen

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nohjason.cheongfordo.R
import com.nohjason.cheongfordo.navigation.Screens
import com.nohjason.cheongfordo.screens.auth.viewmodel.RegisterViewModel
import com.nohjason.cheongfordo.screens.ui.button.MinariButton
import com.nohjason.cheongfordo.screens.ui.text.MinariInputField
import com.nohjason.cheongfordo.ui.theme.MinariBlue500
import com.nohjason.cheongfordo.ui.theme.MinariWhite
import com.nohjason.cheongfordo.ui.theme.h4_bold
import com.nohjason.cheongfordo.ui.theme.b2_medium
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun PasswordScreen(
    navController: NavController? = null, // Preview용 null 허용
    registerViewModel: RegisterViewModel = hiltViewModel()
) {
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isButtonEnabled by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var showPasswordMismatch by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    fun heightRatio(ratio: Float) = screenHeight * ratio
    fun widthRatio(ratio: Float) = screenWidth * ratio

    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
        horizontalAlignment = Alignment.Start
    ) {
        Column(
            modifier = Modifier.padding(horizontal = widthRatio(24f / 360f))
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "뒤로가기",
                modifier = Modifier
                    .padding(top = heightRatio(17f / 640f))
                    .clickable { focusManager.clearFocus() }
            )

            Spacer(modifier = Modifier.height(heightRatio(44f / 640f)))

            Text(
                text = "로그인 시 사용될 비밀번호를 \n입력해 주세요!",
                style = h4_bold
            )

            Spacer(modifier = Modifier.height(heightRatio(76f / 640f)))

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

            Spacer(modifier = Modifier.height(heightRatio(16f / 640f)))

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
                .padding(horizontal = widthRatio(24f / 360f))
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
                    registerViewModel?.updatePassword(newPassword = password)
                    registerViewModel?.updateConfirmPassword(newConfirmPassword = password)
                    navController?.navigate(Screens.EmailScreen.rout)
                    focusManager.clearFocus()
                }
            )
        }

        Spacer(modifier = Modifier.height(heightRatio(8f / 640f)))
    }
}

//@Preview(showBackground = true, widthDp = 360, heightDp = 640)
//@Composable
//fun PasswordScreenPreview() {
//    PasswordScreen()
//}