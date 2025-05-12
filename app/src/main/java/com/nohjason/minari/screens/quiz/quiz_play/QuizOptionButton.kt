package com.nohjason.minari.screens.quiz.quiz_play

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun QuizOptionButton(
    text: String,
    selected: Boolean,
    disabled: Boolean,
    imageRes: Int,
    onClick: () -> Unit
) {
    val bgColor = when {
        selected -> if (text == "네") Color(0xFFB0CDF5) else Color(0xFFFC7C7C)
        else -> Color(0xFFF5F6FA)
    }
    val alpha = if (disabled) 0.4f else 1f

    Column(
        modifier = Modifier
//            .weight(1f)
            .aspectRatio(0.8f)
            .clip(RoundedCornerShape(16.dp))
            .background(bgColor.copy(alpha = alpha))
            .clickable(enabled = !disabled) { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(72.dp)
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF222222)
        )
    }
}
