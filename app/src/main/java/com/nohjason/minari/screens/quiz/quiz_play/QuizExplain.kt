package com.nohjason.minari.screens.quiz.quiz_play

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nohjason.minari.R
import com.nohjason.minari.screens.quiz.data.QuizUiState

@Composable
fun QuizExplain(
    uiState: QuizUiState,
    qtCmt: String,
    qtTip: String,
    onTipClick: () -> Unit
) {
    when (uiState) {
        QuizUiState.Waiting -> {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(R.drawable.emoji_tip),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
                Text(
                    text = "tip",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF222222),
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .clickable { onTipClick() }
                )
            }
            Text(
                text = "tip 아이콘 클릭 시 받게 되는 포인트가 줄어들게 됩니다.",
                fontSize = 13.sp,
                color = Color(0xFF888888)
            )
        }
        QuizUiState.Correct -> {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(R.drawable.emoji_tip),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
                Text(
                    text = "정답",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF363CD5),
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
            Text(
                text = qtCmt,
                fontSize = 13.sp,
                color = Color(0xFF222222)
            )
        }
        QuizUiState.Wrong -> {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(R.drawable.emoji_tip),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
                Text(
                    text = "오답",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFC7C7C),
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
            Text(
                text = qtCmt,
                fontSize = 13.sp,
                color = Color(0xFF222222)
            )
        }
        QuizUiState.Tip -> {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(R.drawable.emoji_tip),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
                Text(
                    text = "tip",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF222222),
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
            Text(
                text = qtTip,
                fontSize = 13.sp,
                color = Color(0xFF888888)
            )
        }
    }
}
