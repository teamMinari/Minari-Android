package com.nohjason.cheongfordo.screens.quiz.quiz_main

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.nohjason.cheongfordo.R
import com.nohjason.cheongfordo.navigation.Screens
import com.nohjason.cheongfordo.screens.quiz.data.PlayData
import com.nohjason.cheongfordo.screens.quiz.data.QuestionResponse
import com.nohjason.cheongfordo.screens.quiz.data.QuizViewModel
import com.nohjason.cheongfordo.screens.quiz.quizmain.PointBox
import com.nohjason.cheongfordo.ui.theme.MinariBlue500
import com.nohjason.cheongfordo.ui.theme.MinariGray200
import com.nohjason.cheongfordo.ui.theme.MinariGray50
import com.nohjason.cheongfordo.ui.theme.MinariGray900
import com.nohjason.cheongfordo.ui.theme.MinariWhite
import com.nohjason.cheongfordo.ui.theme.button_bold
import com.nohjason.cheongfordo.ui.theme.button_medium
import com.nohjason.cheongfordo.ui.theme.caption_bold
import kotlinx.coroutines.launch

@Composable
fun heightPercentage(percent: Float): Dp {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    return screenHeight * percent
}


@Composable
fun QuizMainScreen(
    navHostController: NavHostController,
    quizViewModel: QuizViewModel
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val topPadding = heightPercentage(0.03f)
    val spacerHeight = heightPercentage(0.027f)
    val columnSpacing = heightPercentage(0.054f)
    val innerColumnSpacing = heightPercentage(0.0135f)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = topPadding),
        verticalArrangement = Arrangement.spacedBy(columnSpacing),
    ) {
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(innerColumnSpacing),
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                QuizMainButton(
                    type = "경제퀴즈",
                    onClick = {
                        coroutineScope.launch {
                            val qtAll = quizViewModel.fetchQuestions()
                            val dataList = selectPlayData(qestionAll = qtAll)
                            quizViewModel.initializePlayData(data = dataList)
                            navHostController.navigate(Screens.QuizPlayScreen.rout)
                        }
                    },
                    backgroundColors = listOf(Color(0xFF6889FF), Color(0xFFFF64F5)),
                    iconRes = R.drawable.ic_economyquiz,
                    imageRes = R.drawable.img_economyquiz
                )
                QuizMainButton(
                    type = "복습퀴즈",
                    onClick = {
                        Toast.makeText(context, "아직 준비 중인 기능입니다!", Toast.LENGTH_SHORT).show()
                    },
                    backgroundColors = listOf(Color(0xFF2EDCC4), Color(0xFF266DD3)),
                    iconRes = R.drawable.ic_repitquiz,
                    imageRes = R.drawable.img_repitquiz
                )

                TitleActionRow(navHostController = navHostController)
            }
        }

        item {
            WavyBackgroundBox(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MinariWhite)
                ) {
                    QuizMainAlia(
                        name = "슬기로운 포도알",
                        exp = 1000
                    )
                    Spacer(modifier = Modifier.height(spacerHeight))
                    PointBox(
                        navController = navHostController,
                        point = 2584
                    )
                }
            }
        }
    }
}



@Composable
fun widthPercentage(percent: Float): Dp {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    return screenWidth * percent
}

@Composable
private fun TitleActionRow(
    navHostController: NavHostController,
) {
    val space = widthPercentage(0.04f) // 16.dp ≈ 4% of screen width

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(space)
    ) {
        val url = "https://pf.kakao.com/_xiiQZn"
        val context = LocalContext.current

        ActionItem(
            iconRes = R.drawable.ic_star,
            text = "칭호보기",
            tint = MinariBlue500,
            alpha = 0.64f,
            onClick = {
                navHostController.navigate(Screens.Alias.rout)
            }
        )
        ActionItem(
            iconRes = R.drawable.ic_tell,
            text = "문의하기",
            tint = MinariGray900,
            alpha = 1f,
            onClick = {
                try {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    context.startActivity(intent)
                } catch (e: Exception) {
                    // 예외 처리 (예: 토스트 메시지 등)
                }
            }
        )
    }
}


@Composable
private fun ActionItem(
    @DrawableRes iconRes: Int,
    text: String,
    tint: Color,
    alpha: Float,
    onClick: () -> Unit
) {
    val padding = widthPercentage(0.01f) // 4dp 대체
    val iconSize = widthPercentage(0.04f) // 16dp 대체
    val spacerWidth = widthPercentage(0.01f) // 4dp 대체

    Row(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(padding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = tint,
            modifier = Modifier
                .size(iconSize)
                .alpha(alpha)
        )
        Spacer(modifier = Modifier.width(spacerWidth))
        Text(
            text = text,
            fontSize = 12.sp,
            color = tint,
            modifier = Modifier.alpha(alpha)
        )
    }
}


@Composable
fun QuizMainButton(
    onClick: () -> Unit,
    type: String,
    backgroundColors: List<Color>,
    @DrawableRes iconRes: Int,
    @DrawableRes imageRes: Int
) {
    val height = heightPercentage(0.18f)
    val paddingStart = widthPercentage(0.05f)
    val paddingTop = heightPercentage(0.05f)
    val iconSize = widthPercentage(0.06f)
    val imageSize = widthPercentage(0.45f)
    val offsetX = widthPercentage(0.025f)
    val offsetY = heightPercentage(0.025f)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(widthPercentage(0.04f))) // 16dp 대체
            .background(brush = Brush.horizontalGradient(colors = backgroundColors))
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier.padding(start = paddingStart, top = paddingTop),
            verticalArrangement = Arrangement.spacedBy(heightPercentage(0.01f)) // 8dp 대체
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(iconSize)
            )
            Text(
                text = "$type >",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(imageSize)
                .align(Alignment.BottomEnd)
                .offset(x = offsetX, y = offsetY)
        )
    }
}


@Composable
private fun WavyBackgroundBox(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    // 비율 기반 크기 계산
    val canvasHeight = heightPercentage(0.1f)
    val circleRadius = heightPercentage(0.014f)
    val horizontalPadding = widthPercentage(0.05f)
    val verticalPadding = heightPercentage(0.022f)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MinariGray50)
    ) {
        // 파도 배경 (Canvas)
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(canvasHeight)
                .align(Alignment.TopStart)
        ) {
            val width = size.width
            val height = size.height
            val waveY = height * 0.3f

            val path = Path().apply {
                moveTo(0f, waveY)
                cubicTo(
                    width * 0.25f, height * -0.8f,
                    width * 0.75f, height * 1.2f,
                    width, waveY
                )
                lineTo(width, height)
                lineTo(0f, height)
                close()
            }

            drawPath(path = path, color = MinariWhite)
            drawCircle(
                color = MinariWhite,
                radius = circleRadius.toPx(),
                center = Offset(width * 0.5f, height * -0.08f)
            )
        }

        // 컨텐츠 영역 (흰색 배경)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = canvasHeight * 0.7f) // 파도와 겹치도록 조정
                .background(MinariWhite)
                .padding(horizontal = horizontalPadding, vertical = verticalPadding)
        ) {
            content()
        }
    }
}


@Composable
private fun QuizMainAlia(
    name: String,
    exp: Int,
) {
    val boxSize = widthPercentage(0.27f)
    val imageSize = widthPercentage(0.21f)
    val strokeWidthDp = widthPercentage(0.022f)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(widthPercentage(0.03f)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(boxSize),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.size(boxSize)) {
                val strokeWidthPx = strokeWidthDp.toPx()
                drawArc(
                    color = Color(0xFFE0E3ED),
                    startAngle = 0f,
                    sweepAngle = 360f,
                    useCenter = false,
                    style = Stroke(width = strokeWidthPx, cap = StrokeCap.Round)
                )
                val percentage = 0.5f // exp/totalExp 계산 가능
                drawArc(
                    color = Color(0xFF00D33B),
                    startAngle = -90f,
                    sweepAngle = 360f * percentage,
                    useCenter = false,
                    style = Stroke(width = strokeWidthPx, cap = StrokeCap.Round)
                )
            }
            Image(
                painter = painterResource(id = R.drawable.img_economyquiz),
                contentDescription = null,
                modifier = Modifier
                    .size(imageSize)
                    .clip(CircleShape)
            )
        }

        Spacer(modifier = Modifier.weight(0.1f))

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = button_bold.toSpanStyle()) {
                        append("\"${name}\"")
                    }
                    withStyle(style = button_medium.toSpanStyle()) {
                        append("을 얻기까지\n${exp}exp가 남았습니다!")
                    }
                },
                color = MinariGray900
            )

            Spacer(modifier = Modifier.height(heightPercentage(0.008f))) // 6dp 대체 (약 0.8%)

            Row(
                horizontalArrangement = Arrangement.End
            ) {
                Box(
                    modifier = Modifier
                        .border(
                            width = widthPercentage(0.003f), // 1dp 대체
                            color = MinariGray200,
                            shape = RoundedCornerShape(
                                topStart = widthPercentage(0.05f),  // 20dp 대체
                                topEnd = widthPercentage(0.05f),
                                bottomStart = widthPercentage(0.05f),
                                bottomEnd = 0.dp
                            )
                        )
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(
                                topStart = widthPercentage(0.05f),
                                topEnd = widthPercentage(0.05f),
                                bottomStart = 0.dp,
                                bottomEnd = widthPercentage(0.05f)
                            )
                        )
                        .padding(
                            horizontal = widthPercentage(0.05f), // 20dp 대체
                            vertical = heightPercentage(0.014f)  // 10dp 대체
                        ),
                ) {
                    Text(
                        text = "안녕, 난 농사 짓는 포도알이야~",
                        style = caption_bold,
                        color = MinariGray900,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}


fun selectPlayData(qestionAll: QuestionResponse): PlayData {
    val qtSelected = qestionAll.data.shuffled().take(10)
    println(qtSelected)
    return PlayData(
        userCurrent = 0,         // 현재 유저 진행 상황, 0으로 초기화
        point = 0,               // 초기 포인트, 0으로 초기화
        qtNum = 0,               // 첫 번째 문제부터 시작, 0으로 초기화
        qtList = qtSelected // 10개의 질문을 담은 리스트
    )
}



//@Preview(showBackground = true)
//@Composable
//fun PreQuizMainScreen() {
//    QuizMainScreen(
//        navHostController = rememberNavController()
//    )
//}
