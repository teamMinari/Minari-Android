package com.nohjason.cheongfordo.screens.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nohjason.cheongfordo.ui.theme.MinariGray200
import com.nohjason.cheongfordo.ui.theme.MinariGray900
import com.nohjason.cheongfordo.ui.theme.button_medium

@Composable
fun MinariChatText(
    text: String,
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
                .padding(horizontal = 20.dp, vertical = 10.dp),
        ) {
            Text(
                text = text,
                style = button_medium,
                color = MinariGray900,
                textAlign = TextAlign.Center,
            )
        }
    }
}

//@Preview
//@Composable
//fun PreChatText() {
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center
//    ) {
//        MinariChatText(text = "안녕하세요!") // 받은 메시지
//        Spacer(modifier = Modifier.height(8.dp))
//    }
//}
