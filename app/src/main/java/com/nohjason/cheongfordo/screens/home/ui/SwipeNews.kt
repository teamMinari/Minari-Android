package com.nohjason.cheongfordo.screens.home.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nohjason.cheongfordo.screens.home.data.ShowNews
import com.nohjason.cheongfordo.ui.theme.MinariGray600
import com.nohjason.cheongfordo.ui.theme.MinariGray800
import com.nohjason.cheongfordo.ui.theme.MinariWhite
import com.nohjason.cheongfordo.ui.theme.button_bold
import com.nohjason.cheongfordo.ui.theme.caption_bold

@Composable
fun VerticalNewsList(newsList: List<ShowNews>) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        newsList.forEach { news ->
            NewsItem(news = news, onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(news.link))
                context.startActivity(intent)
            })
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}


@Composable
fun NewsItem(
    news: ShowNews,
    onClick: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .height(230.dp)
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = MinariWhite)
            .clickable {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(news.link))
                context.startActivity(intent)
            }
    ) {
        AsyncImage(
            model = news.img,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(143.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = news.title,
                color = MinariGray800,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = button_bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = news.company,
                color = MinariGray600,
                style = caption_bold
            )
        }
    }
}

//@OptIn(ExperimentalPagerApi::class)
//@Composable
//fun NewsItem(
//    news: ShowNews,
//    onClick: () -> Unit
//) {
//    val context = LocalContext.current
//    val pagerState = rememberPagerState()
//
//    HorizontalPager(
//        count = list.size,
//        state = pagerState,
//        itemSpacing = 20.dp
//    ) { page ->
//        // 페이지 내용
//        Column(
//            modifier = Modifier
//                .height(240.dp) // 높이를 늘려 뉴스 카드가 잘리지 않도록 함
//                .padding(horizontal = 20.dp)
//                .clip(RoundedCornerShape(10.dp))
//                .background(color = MinariWhite)
//        ) {
//            AsyncImage(
//                model = news.img,
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(143.dp)
//                    .clickable {
//                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(news.link))
//                        context.startActivity(intent)
//                    },
//            )
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                verticalArrangement = Arrangement.Bottom
//            ) {
//                Spacer(modifier = Modifier.height(10.dp)) // 간격 조정
//                Text(
//                    text = news.title,
//                    color = MinariGray800,
//                    maxLines = 2, // 최대 2줄로 제한하여 잘리지 않도록 함
//                    overflow = TextOverflow.Ellipsis,
//                    style = button_bold
//                )
//                Spacer(modifier = Modifier.height(12.dp))
//                Text(
//                    text = news.company,
//                    color = MinariGray600,
//                    style = caption_bold
//                )
//            }
//        }
//    }
//
//}