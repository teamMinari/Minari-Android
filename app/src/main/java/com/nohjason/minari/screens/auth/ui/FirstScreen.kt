package com.nohjason.minari.screens.auth.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nohjason.minari.screens.auth.viewmodel.LoginViewModel
import kotlinx.coroutines.delay
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.nohjason.minari.R
import com.nohjason.minari.navigation.Screens
import com.nohjason.minari.screens.ui.button.MinariButton
import com.nohjason.minari.screens.ui.text.MinariText
import com.nohjason.minari.ui.theme.MinariBlue
import com.nohjason.minari.ui.theme.MinariBlue500
import com.nohjason.minari.ui.theme.MinariGray200
import com.nohjason.minari.ui.theme.MinariGray500
import com.nohjason.minari.ui.theme.MinariGray900
import com.nohjason.minari.ui.theme.MinariWhite
import com.nohjason.minari.ui.theme.h4_medium
import com.nohjason.minari.ui.theme.rixfont


@Composable
fun FirstScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val density = LocalDensity.current
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    var isFirstChatVisible by remember { mutableStateOf(false) }
    var isSecondChatVisible by remember { mutableStateOf(false) }
    var contentOffset by remember { mutableStateOf(screenHeight * 0.06f) }

    val context = LocalContext.current

    var characterOffset by remember { mutableStateOf(screenHeight * 0.2f) }
    val animatedCharacterOffset by animateDpAsState(
        targetValue = characterOffset,
        animationSpec = tween(durationMillis = 300), label = "characterOffset"
    )

    val animatedContentOffset by animateDpAsState(
        targetValue = contentOffset,
        animationSpec = tween(durationMillis = 300), label = "contentOffset"
    )

    var isAnimationRunning by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(500)
        isFirstChatVisible = true
        delay(900)
        isSecondChatVisible = true
        delay(1000)
        characterOffset = 0.dp
        contentOffset = 0.dp
        delay(500)
        characterOffset = screenHeight * 0.07f
        isAnimationRunning = false
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = screenHeight * 0.04f, vertical = screenHeight * 0.03f),
    ) {
        Spacer(modifier = Modifier.height(screenHeight * 0.02f))

        Column {
            AnimatedVisibility(visible = isSecondChatVisible) {
                Column {
                    LogoChatText(text = "청포도는 처음이야?", logo = false)
                    Spacer(modifier = Modifier.height(screenHeight * 0.01f))
                }
            }

            AnimatedVisibility(visible = isFirstChatVisible) {
                LogoChatText(text = "에 온걸 환영해", logo = true)
            }
        }

        Spacer(modifier = Modifier.height(screenHeight * 0.16f))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .offset(y = animatedContentOffset)
                .animateContentSize()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(R.drawable.grape),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(screenHeight * 0.05f)
                )
                MinariText(
                    text = "청포도",
                    color = MinariBlue,
                    fontFamily = rixfont,
                    size = 40
                )
            }

            Spacer(modifier = Modifier.height(screenHeight * 0.25f))

            MinariButton(
                text = "네, 사용해 본 적이 있어요.",
                size = "Large",
                textColor = MinariWhite,
                buttonColor = MinariBlue500,
                line = false,
                enabled = !isAnimationRunning,
                modifier = Modifier.fillMaxWidth()
            ) {
                navController.navigate(Screens.Login.rout)
            }

            Spacer(modifier = Modifier.height(screenHeight * 0.01f))

            MinariButton(
                text = "아니요, 이번이 처음이에요.",
                size = "Large",
                textColor = MinariGray500,
                buttonColor = MinariWhite,
                line = true,
                enabled = !isAnimationRunning,
                modifier = Modifier.fillMaxWidth()
            ) {
                navController.navigate(Screens.IdScreen.rout)
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_first_screen),
            contentDescription = "청포도 캐릭터",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(360f / 164f)
                .offset(y = animatedCharacterOffset)
        )
    }
}

@Composable
private fun LogoChatText(
    text: String,
    logo: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Box(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = MinariGray200,
                    shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 0.dp)
                )
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 0.dp)
                )
                .padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            Row {
                if (logo) {
                    MinariText(
                        text = "청포도",
                        color = MinariBlue,
                        fontFamily = rixfont,
                        size = 18,
                        modifier = Modifier.padding(end = 3.dp)
                    )
                }
                Text(
                    text = text,
                    style = h4_medium,
                    color = MinariGray900,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewFirstScreen() {
//    FirstScreen(navController = rememberNavController())
//}