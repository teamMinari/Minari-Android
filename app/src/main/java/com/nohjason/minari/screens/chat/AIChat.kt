package com.nohjason.minari.screens.chat

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import com.mohamedrejeb.richeditor.ui.material3.RichText
import com.nohjason.minari.R
import com.nohjason.minari.ui.theme.MinariBlue200
import com.nohjason.minari.ui.theme.MinariBlue800
import com.nohjason.minari.ui.theme.MinariBlue900
import com.nohjason.minari.ui.theme.b2_bold

@Composable
fun AiChatText(text: String) {
    val richTextState = rememberRichTextState()

    Column {
        Row {
            Icon(
                painter = painterResource(R.drawable.ic_ai),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Text(
                text = "AI챗봇",
                color = MinariBlue900,
                style = b2_bold
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        RichText(
            state = richTextState.setMarkdown(text),
        )
    }

}

@Composable
fun AiLoadingAnimation() {
    val dotCount = remember { mutableStateOf(1) }
    val infiniteTransition = rememberInfiniteTransition(label = "dots")
    val anim by infiniteTransition.animateValue(
        initialValue = 1,
        targetValue = 3,
        typeConverter = Int.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(600, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "dots"
    )
    LaunchedEffect(anim) { dotCount.value = anim }

    val loadingText = "주호님께 더 나은 설명을 하기 위해 고민중 " + ".".repeat(dotCount.value)
    AiChatText(loadingText)
}