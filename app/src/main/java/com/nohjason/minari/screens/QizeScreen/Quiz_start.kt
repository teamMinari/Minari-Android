package com.nohjason.minari.screens.QizeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nohjason.minari.R
import com.nohjason.minari.navigation.BottomNavGraph
import com.nohjason.minari.ui.theme.MinariBlue
import com.nohjason.minari.ui.theme.MinariPurple
import com.nohjason.minari.ui.theme.gradation

//폰트
val pretendardFamily = FontFamily(
    Font(R.font.pretendard_regular, FontWeight.Thin),
    Font(R.font.pretendard_medium, FontWeight.Medium),
    Font(R.font.pretendard_semibold, FontWeight.SemiBold),
    Font(R.font.pretendard_bold, FontWeight.Bold)
)

@Composable
fun Point_box() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.1475f)
            .background(
                brush = Brush.linearGradient(
                    colors = gradation,
                    start = androidx.compose.ui.geometry.Offset(1300f, 800f),
                    end = androidx.compose.ui.geometry.Offset(300f, 0f),
                ),
                shape = RoundedCornerShape(
                    topStart = CornerSize(0.dp), topEnd = CornerSize(0.dp),
                    bottomEnd = CornerSize(50.dp), bottomStart = CornerSize(50.dp)
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Row (verticalAlignment = Alignment.CenterVertically){
            Text(text = "My 포인트",
                fontSize = 20.sp, fontFamily = pretendardFamily, fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
            Text(text = "1108",
                modifier = Modifier.padding(start = 38.dp),
                fontSize = 40.sp,fontFamily = pretendardFamily, fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Composable
fun QuizScreen_start() {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ){
        Point_box()

        Text(
            text = "퀴즈를 시작하겠습니다!",
            modifier = Modifier
                .padding(top = 194.dp)
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 30.sp, fontWeight = FontWeight.SemiBold, fontFamily = pretendardFamily,
            color = Color.Black
        )

        Text(
            text = "총 10문제이며 O/X 형식으로 이루어져있습니다.",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp)
                .align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Medium, fontFamily = pretendardFamily,
            color = MinariBlue
        )

        Button(
            onClick = { /* TODO */ },
            modifier = Modifier
                .padding(top = 30.dp)
                .align(Alignment.CenterHorizontally)
                .width(310.dp)
                .height(46.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MinariBlue
            )
        ) {
            Text(
                text = "도전하기",
                color = Color.White,
                fontFamily = pretendardFamily,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun QuizScreen_not_start(){
    val guide_text = buildAnnotatedString {
        append("현재")
        withStyle(style = SpanStyle(color = MinariBlue)) {
            append("자기 단어장")
        }
        append("에 저장된 단어가\n없어서 풀 수 없어요!")
    }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ){
        Point_box()
        Image(
            painterResource(id = R.drawable.emoji_tired), contentDescription = null,
            modifier = Modifier
                .fillMaxSize(0.45f)
                .padding(top = 133.dp))

        Text(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(top = 27.dp),
            text = guide_text,
            textAlign = TextAlign.Center,
            fontFamily = pretendardFamily,
            fontWeight = FontWeight.SemiBold
        )

        Button(onClick = { /*TODO*/ },
            modifier = Modifier
                .width(305.dp)
                .padding(top = 27.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF767AEE)
        )
            ) {
                Text(text = "단어 저장하러 가기 >",
                    fontFamily = pretendardFamily,
                    fontWeight = FontWeight.Bold
                )
        }
    }
}
