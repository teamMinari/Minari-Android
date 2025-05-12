package com.nohjason.minari.screens.auth.ui.register_screen

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nohjason.minari.R
import com.nohjason.minari.navigation.Screens
import com.nohjason.minari.screens.auth.viewmodel.RegisterViewModel
import com.nohjason.minari.screens.ui.button.MinariButton
import com.nohjason.minari.screens.ui.text.MinariInputField
import com.nohjason.minari.ui.theme.MinariBlue500
import com.nohjason.minari.ui.theme.MinariWhite
import com.nohjason.minari.ui.theme.h4_bold
import androidx.compose.ui.platform.*
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun EmailScreen(
    navController: NavController? = null, // Preview용 null 허용
    registerViewModel: RegisterViewModel? = null
) {
    var textState by remember { mutableStateOf("") }
    var isButtonEnabled by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    fun heightRatio(ratio: Float) = screenHeight * ratio
    fun widthRatio(ratio: Float) = screenWidth * ratio

    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
            .background(color = MinariWhite),
        horizontalAlignment = Alignment.Start
    ) {
        Column(
            modifier = Modifier.padding(horizontal = widthRatio(24f / 360f))
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "뒤로가기",
                tint = Color.Unspecified,
                modifier = Modifier
                    .padding(top = heightRatio(17f / 640f))
                    .clickable { focusManager.clearFocus() }
            )

            Spacer(modifier = Modifier.height(heightRatio(44f / 640f)))

            Text(
                text = "로그인 시 사용될 이메일을 \n작성해 주세요!",
                style = h4_bold
            )

            Spacer(modifier = Modifier.height(heightRatio(76f / 640f)))

            MinariInputField(
                icon = null,
                label = "이메일 입력",
                onValueChange = {
                    textState = it
                    isButtonEnabled = it.isNotEmpty() && isValidEmail(it)
                }
            )
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
                    focusManager.clearFocus()
                    if (!isValidEmail(textState)) {
                        Toast.makeText(context, "올바른 이메일 형식을 입력해 주세요", Toast.LENGTH_SHORT).show()
                        return@MinariButton
                    }
                    registerViewModel?.updateEmail(newEmail = textState)
                    navController?.navigate(Screens.LikeScreen.rout)
                }
            )
        }

        Spacer(modifier = Modifier.height(heightRatio(8f / 640f)))
    }
}

//@Preview(showBackground = true, widthDp = 360, heightDp = 640)
//@Composable
//fun EmailScreenPreview() {
//    EmailScreen()
//}