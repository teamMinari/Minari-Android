package com.nohjason.minari.screens.qize

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nohjason.minari.R
import com.nohjason.minari.ui.theme.MinariBlue

@Preview
@Composable
fun QuizScreen_play() {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ){
        val qize_page = 2
        val qize_problem = "은행예금이나 보험이 모두 저축을 목\n적으로 한다는 점에서는 똑같다."

        IconButton(onClick = { /*TODO*/ },
                modifier = Modifier.padding(end = 361.dp)
            ) {
            Image(painterResource(id = R.drawable.befor_arrow), contentDescription = null)
        }

        Row (modifier = Modifier.padding(end = 272.dp, top = 60.dp)){
            Text(text = qize_page.toString(),
                fontFamily = pretendardFamily,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3B88FB)
            )
            Text(text = "/10",
                fontFamily = pretendardFamily,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3B88FB))
        }

        Text(text = qize_problem,
            fontFamily = pretendardFamily,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 5.dp, end = 110.dp))


        Row (modifier = Modifier.padding(top = 35.dp)) {
            TextButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .wrapContentSize()
                    .width(150.dp)
                    .height(180.dp)
                    .background(color = Color(0xFFEAEAEA), shape = RoundedCornerShape(10.dp))
            ) {
                Image(painterResource(id = R.drawable.qize_o), contentDescription = null)
            }

            TextButton(
                onClick = {},
                modifier = Modifier
                    .wrapContentSize()
                    .width(150.dp)
                    .height(180.dp)
                    .padding(start = 5.dp)
                    .background(color = Color(0xFFEAEAEA), shape = RoundedCornerShape(10.dp))
            ) {
                Image(painterResource(id = R.drawable.qize_x), contentDescription = null)

            }
        }

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .width(305.dp)
                .padding(top = 100.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MinariBlue)
        ) {
            Text(text = "다음")
        }
    }
}



@Composable
fun Commentary_O(){
    Column (horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()) {
        Text(text = "정답!",
            Modifier.padding(end = 250.dp, top = 65.dp),
            fontFamily = pretendardFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 17.sp,
            color = MinariBlue)

        Text(text = "은행예금은 저축을 목적으로 하지만,\n" +
                "보험은 만약에 생길지도 모르는 사고위험에 \n" +
                "대한 보장을 목적으로 합니다.",
                Modifier.padding(end = 40.dp),
                fontFamily = pretendardFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp
            )
    }
}

@Composable
fun Commentary_X(){
    Column (horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()) {
        Text(text = "오답!",
            Modifier.padding(end = 250.dp, top = 65.dp),
            fontFamily = pretendardFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 17.sp,
            color = MinariBlue)

        Text(text = "은행예금은 저축을 목적으로 하지만,\n" +
                "보험은 만약에 생길지도 모르는 사고위험에 \n" +
                "대한 보장을 목적으로 합니다.",
            Modifier.padding(end = 40.dp),
            fontFamily = pretendardFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp
        )
    }
}