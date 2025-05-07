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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nohjason.minari.R
import com.nohjason.minari.navigation.Screens
import com.nohjason.minari.navigation.bottombar.BottomScreen
import com.nohjason.minari.preferences.PreferencesManager
import com.nohjason.minari.screens.auth.viewmodel.LoginViewModel
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
import kotlinx.coroutines.delay
@Composable
fun FirstScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = viewModel()
) {
    var isFirstChatVisible by remember { mutableStateOf(false) }
    var isSecondChatVisible by remember { mutableStateOf(false) }
    var contentOffset by remember { mutableStateOf(50.dp) }

    val context = LocalContext.current

    // 애니메이션 적용된 캐릭터 Y 좌표
    var characterOffset by remember { mutableStateOf(150.dp) }
    val animatedCharacterOffset by animateDpAsState(
        targetValue = characterOffset,
        animationSpec = tween(durationMillis = 300)
    )

    // 니메이션 적용된 콘텐츠 Y 좌표
    val animatedContentOffset by animateDpAsState(
        targetValue = contentOffset,
        animationSpec = tween(durationMillis = 300)
    )

    // 애니메이션 실행 여부 상태
    var isAnimationRunning by remember { mutableStateOf(true) }

    // 애니메이션 실행 순서
    LaunchedEffect(Unit) {
        delay(500)
        isFirstChatVisible = true
        delay(900)
        isSecondChatVisible = true
        delay(1000)
        characterOffset = 0.dp
        contentOffset = 0.dp
        delay(500)
        characterOffset = 50.dp
        isAnimationRunning = false // 애니메이션 종료
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 35.dp, vertical = 24.dp),
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Column {
            AnimatedVisibility(visible = isSecondChatVisible) {
                Column {
                    LogoChatText(text = "청포도는 처음이야?", logo = false)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            AnimatedVisibility(visible = isFirstChatVisible) {
                Column {
                    LogoChatText(text = "에 온걸 환영해", logo = true)
                }
            }
        }

        Spacer(modifier = Modifier.height(130.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .offset(y = animatedContentOffset)
                .animateContentSize()
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    painter = painterResource(R.drawable.grape),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(38.dp)
                )
                MinariText(
                    text = "청포도",
                    color = MinariBlue,
                    fontFamily = rixfont,
                    size = 40
                )
            }

            Spacer(modifier = Modifier.height(200.dp))

            MinariButton(
                text = "네, 사용해 본 적이 있어요.",
                size = "Large",
                textColor = MinariWhite,
                buttonColor = MinariBlue500,
                line = false,
                enabled = true,
                modifier = Modifier
                    .fillMaxWidth()
                // 애니메이션 중에는 클릭 막기
            ) {
                if (!isAnimationRunning) {
                    navController.navigate(Screens.Login.rout)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            MinariButton(
                text = "아니요, 이번이 처음이에요.",
                size = "Large",
                textColor = MinariGray500,
                buttonColor = MinariWhite,
                line = true,
                enabled = true,
                modifier = Modifier
                    .fillMaxWidth()
                // 애니메이션 중에는 클릭 막기
            ) {
                if (!isAnimationRunning) {
                    navController.navigate(Screens.IdScreen.rout)
                }
            }
        }
    }

    // 캐릭터 이미지 (애니메이션 적용됨)
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_first_screen),
            contentDescription = "청포도 캐릭터",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(360f / 164f)
                .offset(y = animatedCharacterOffset) // 애니메이션 적용된 값 사용
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
                    shape = RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp,
                        bottomStart = 20.dp,
                        bottomEnd = 0.dp
                    )
                )
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp,
                        bottomStart = 20.dp,
                        bottomEnd = 0.dp
                    )
                )
                .padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            Row {
                if (logo) {
                    Box(
                        modifier = Modifier
                            .padding(end = 3.dp)
                    ){
                        MinariText(
                            text = "청포도",
                            color = MinariBlue,
                            fontFamily = rixfont,
                            size = 18
                        )
                    }
                }
                Text(
                    text = text,
                    style = h4_medium,
                    color = MinariGray900,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun PreFirstScreen() {
//    FirstScreen()
//}
