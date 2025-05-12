package com.nohjason.minari.screens.quiz.quiz_main

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nohjason.minari.R
import com.nohjason.minari.screens.quiz.QuizButton
import com.nohjason.minari.screens.quiz.data.PlayData
import com.nohjason.minari.screens.quiz.data.QuestionResponse
import com.nohjason.minari.screens.quiz.data.QuizViewModel
import com.nohjason.minari.screens.quiz.quizmain.PointBox
import com.nohjason.minari.screens.ui.text.MinariChatText
import com.nohjason.minari.ui.theme.MinariBlue
import com.nohjason.minari.ui.theme.MinariBlue500
import com.nohjason.minari.ui.theme.MinariGray200
import com.nohjason.minari.ui.theme.MinariGray50
import com.nohjason.minari.ui.theme.MinariGray500
import com.nohjason.minari.ui.theme.MinariGray900
import com.nohjason.minari.ui.theme.MinariWhite
import com.nohjason.minari.ui.theme.b2_bold
import com.nohjason.minari.ui.theme.button_bold
import com.nohjason.minari.ui.theme.button_medium
import com.nohjason.minari.ui.theme.caption_bold
import kotlinx.coroutines.launch
import kotlin.math.exp

@Composable
fun QuizMainScreen(
    navHostController: NavHostController,
//    quizViewModel: QuizViewModel? = null
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
//            .background(MinariWhite) // 최상위 배경을 흰색으로 설정
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState) // 스크롤 활성화
                .padding(top = 66.dp),
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                QuizMainButton(
                    type = "경제퀴즈",
                    onClick = {},
                    backgroundColors = listOf(Color(0xFF6889FF), Color(0xFFFF64F5)),
                    iconRes = R.drawable.ic_economyquiz,
                    imageRes = R.drawable.img_economyquiz
                )
                QuizMainButton(
                    type = "복습퀴즈",
                    onClick = {},
                    backgroundColors = listOf(Color(0xFF2EDCC4), Color(0xFF266DD3)),
                    iconRes = R.drawable.ic_repitquiz,
                    imageRes = R.drawable.img_repitquiz
                )

                TitleActionRow()
            }

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
                    Spacer(modifier = Modifier.height(20.dp))
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
private fun TitleActionRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ActionItem(
            iconRes = R.drawable.ic_star,
            text = "칭호보기",
            tint = MinariBlue500,
            alpha = 0.64f,
            onClick = {}
        )
        ActionItem(
            iconRes = R.drawable.ic_tell,
            text = "문의하기",
            tint = MinariGray900,
            alpha = 1f,
            onClick = {}
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
    Row(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = tint,
            modifier = Modifier
                .size(16.dp)
                .alpha(alpha)
        )
        Spacer(modifier = Modifier.width(4.dp))
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
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(brush = Brush.horizontalGradient(colors = backgroundColors))
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier.padding(start = 20.dp, top = 20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(24.dp)
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
                .size(180.dp)
                .align(Alignment.BottomEnd)
                .offset(x = 10.dp, y = 10.dp)
        )
    }
}

@Composable
private fun WavyBackgroundBox(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    Column(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = screenHeight) // 화면 전체 높이 이상 확보
            .background(MinariGray50)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
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
            drawRect(
                color = MinariWhite,
                topLeft = Offset(0f, waveY),
                size = androidx.compose.ui.geometry.Size(width, height - waveY)
            )
            drawCircle(
                color = MinariWhite,
                radius = 10.dp.toPx(),
                center = Offset(width * 0.5f, height * -0.08f)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // 남은 공간 모두 차지
                .background(MinariWhite)
                .padding(horizontal = 20.dp, vertical = 16.dp)
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
    val boxSize = 108.dp       // 72.dp * 1.5
    val imageSize = 84.dp      // 56.dp * 1.5
    val strokeWidth = 9f       // 6f * 1.5

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(boxSize),
            contentAlignment = Alignment.Center
        ) {
            // 배경 원형 테두리
            Canvas(modifier = Modifier.size(boxSize)) {
                drawArc(
                    color = Color(0xFFE0E3ED),
                    startAngle = 0f,
                    sweepAngle = 360f,
                    useCenter = false,
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                )
                // 예시로 50% 진행 (exp에 따라 조정)
                val percentage = 0.5f // exp/totalExp로 계산 가능
                drawArc(
                    color = Color(0xFF00D33B),
                    startAngle = -90f,
                    sweepAngle = 360f * percentage,
                    useCenter = false,
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                )
            }
            // 캐릭터 이미지 (동그랗게)
            Image(
                painter = painterResource(id = R.drawable.img_economyquiz),
                contentDescription = null,
                modifier = Modifier
                    .size(imageSize)
                    .clip(CircleShape)
            )
        }
        Spacer(modifier = Modifier.weight(0.1f))
        // 텍스트 영역
        Column(
            modifier = Modifier
                .weight(1f),
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

            Spacer(modifier = Modifier.height(6.dp))
            // 말풍선 스타일
            Row(
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
                                bottomStart = 0.dp,
                                bottomEnd = 20.dp
                            )
                        )
                        .padding(horizontal = 20.dp, vertical = 10.dp),
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



@Preview(showBackground = true)
@Composable
fun PreQuizMainScreen() {
    QuizMainScreen(
        navHostController = rememberNavController()
    )
}
