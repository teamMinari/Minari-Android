package com.nohjason.minari.screens.news

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.nohjason.minari.screens.rout.response.NewsData
import com.nohjason.minari.ui.theme.MinariGray900
import com.nohjason.minari.ui.theme.MinariWhite
import com.nohjason.minari.ui.theme.b1_bold
import com.nohjason.minari.ui.theme.button_bold
import com.nohjason.minari.ui.theme.button_medium

@Composable
fun HotNewsCard(
    item: NewsData,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(307.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .padding(bottom = 12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
        ) {
            AsyncImage(
                model = item.thumbnail,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )
            // 하단 그라데이션
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .align(Alignment.BottomCenter)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.6f))
                        )
                    )
            )
            // HOT 뱃지 (좌하단)
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 12.dp, bottom = 12.dp)
                    .background(Color(0x4DFFFFFF), shape = RoundedCornerShape(100.dp))
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "\uD83D\uDD25HOT",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        // 제목
        Text(
            text = item.title ?: "제목 없음",
            color = MinariGray900,
            style = b1_bold,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        // 시간
        Text(
            text = item.uploadTime?: "시간 없음",
            color = Color(0xFF9E9E9E),
            fontSize = 13.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFF5F6FA)
@Composable
fun HotNewsCardPreview() {
    // 샘플 데이터 생성
    val sampleNews = NewsData(
        title = "난 왜 학교에? 아이들 폭동 일으켜(대구 소프트 웨어 마이스터 고등학교)",
        url = "https://n.news.naver.com/mnews/article/277/0005588814",
        company = "코틀린타임즈",
        thumbnail = "https://mimgnews.pstatic.net/image/origin/277/2025/05/07/5588814.jpg?type=nf220_150",
        uploadTime = "2023-10-25 14:30"
    )

    HotNewsCard(
        item = sampleNews,
        onClick = { /* 클릭 동작 처리 */ }
    )
}
