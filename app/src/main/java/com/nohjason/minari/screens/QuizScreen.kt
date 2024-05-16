package com.nohjason.minari.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.widget.ConstraintLayout
import com.nohjason.minari.R

@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun QuizScreen_main() {
    Column {
        //폰트
        val pretendardFamily = FontFamily(
            Font(R.font.pretendard_regular, FontWeight.Thin),
            Font(R.font.pretendard_medium, FontWeight.Medium),
            Font(R.font.pretendard_semibold, FontWeight.SemiBold),
            Font(R.font.pretendard_bold, FontWeight.Bold)
        )

        //첫번째 텍스트
        Text(
            text = "퀴즈를 시작하겠습니다!",
            modifier = Modifier
                .padding(top = 400.dp)
            ,
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
//                fontWeight = FontWeight.SemiBold,
            fontFamily = pretendardFamily,
            color = Color.Black,
        )

        Text(
            text = "총 10문제이며 O/X 형식으로 이루어져있습니다.",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(top = 193.dp),
            fontWeight = FontWeight.Medium,
            fontFamily = pretendardFamily,
            color = Color(0xFF363CD5)
        )



        Button(
            onClick = { /* TODO */ },
            modifier = Modifier
                .align(Alignment.Center).width(310.dp).height(46.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF363CD5)
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