package com.nohjason.cheongfordo.screens.news

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nohjason.cheongfordo.screens.rout.response.NewsData
import com.nohjason.cheongfordo.ui.theme.MinariGray400
import com.nohjason.cheongfordo.ui.theme.MinariGray900
import com.nohjason.cheongfordo.ui.theme.button_bold
import com.nohjason.cheongfordo.ui.theme.button_medium

@Composable
fun News(
    newsList: List<NewsData>
) {
    val context = LocalContext.current

    if (newsList.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier
                .padding(top = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            newsList.forEach { item ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable(enabled = !item.url.isNullOrBlank()) {
                            try {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
                                context.startActivity(intent)
                            } catch (e: Exception) {
                                Log.e("News", "Intent 실행 중 예외: ${e.message}")
                            }
                        }
                        .padding(horizontal = 20.dp),
                ) {
                    if (!item.thumbnail.isNullOrBlank()) {
                        Box(
                            modifier = Modifier
                                .height(70.dp)
                                .clip(RoundedCornerShape(10.dp))
                        ) {
                            AsyncImage(
                                model = item.thumbnail,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.width(110.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                    Column {
                        Text(
                            text = item.title ?: "제목 없음",
                            color = MinariGray900,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = button_bold
                        )
                        Text(
                            text = item.uploadTime?: "시간 없음",
                            color = MinariGray400,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = button_medium
                        )
                    }
                }
            }
        }
    }
}







//@Preview(showSystemUi = true)
//@Composable
//private fun PreNews() {
//    News(navController = rememberNavController())
//}