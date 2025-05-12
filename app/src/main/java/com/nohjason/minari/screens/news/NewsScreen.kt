package com.nohjason.minari.screens.news

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.nohjason.minari.R
import com.nohjason.minari.navigation.bottombar.BottomScreen
import com.nohjason.minari.screens.rout.response.NewsData
import com.nohjason.minari.screens.ui.button.NewsButton
import com.nohjason.minari.ui.theme.MinariBlue500
import com.nohjason.minari.ui.theme.MinariGray200
import com.nohjason.minari.ui.theme.MinariGray400
import com.nohjason.minari.ui.theme.MinariGray900
import com.nohjason.minari.ui.theme.b2_bold
import com.nohjason.minari.ui.theme.button_bold
import com.nohjason.minari.ui.theme.button_medium


@OptIn(ExperimentalPagerApi::class)
@Composable
fun NewsScreen(
    navController: NavController,
    newsViewModel: NewsViewModel = hiltViewModel()
) {
    var selectedCategory by remember { mutableStateOf("security") }
    val listState = rememberLazyListState()
    val pagerState = rememberPagerState(initialPage = 0)

    // 뉴스 로드
    LaunchedEffect(selectedCategory) {
        newsViewModel.getCategoryNews(selectedCategory)
    }
    LaunchedEffect(Unit) {
        newsViewModel.getHotNews()
    }
    val hotNewsList = newsViewModel.hotNews.collectAsState().value?.data?.take(3) ?: emptyList()
    val categoryNews = newsViewModel.categoryNews.collectAsState().value
    val categoryNewsList = categoryNews?.data ?: emptyList()


    LazyColumn(
        state = listState,
        modifier = Modifier
            .padding(start = 25.dp, end = 25.dp, top = 60.dp)
            .fillMaxSize()
            .background(Color(0xFFF5F6FA))
    ) {
        item {
            if (hotNewsList.isNotEmpty()) {
                HorizontalPager(
                    count = hotNewsList.size,
                    state = pagerState
                ) { page ->
                    HotNewsCard(
                        item = hotNewsList[page],
                        onClick = { /* 원하는 동작 */ }
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(hotNewsList.size) { index ->
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .size(8.dp)
                                .background(
                                    color = if (index == pagerState.currentPage) MinariBlue500.copy(alpha = 0.65f) else MinariGray200,
                                    shape = CircleShape
                                )
                        )
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }

        item {
            Text(
                text = "뉴스 태그 검색",
                style = b2_bold,
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val newsCategories = listOf(
                    Triple(R.drawable.img_finance, "금융", "security"),
                    Triple(R.drawable.img_certificate, "증권", "finance"),
                    Triple(R.drawable.img_property, "산업/재계", "economy"),
                    Triple(R.drawable.img_real_estate, "부동산", "real_estate"),
                    Triple(R.drawable.img_global_economy, "글로벌\n경제", "industrial_business")
                )

                newsCategories.forEach { (iconResId, text, category) ->
                    val isSelected = selectedCategory == category

                    NewsButton(
                        icon = painterResource(id = iconResId),
                        text = text,
                        isSelected = isSelected,
                        onClick = { selectedCategory = category }
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
        }

        item {
            when {
                categoryNews == null -> {
                    // 로딩 중
                    Box(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                categoryNewsList.isEmpty() -> {
                    // 데이터 없음
                    Text(
                        text = "뉴스가 없습니다.",
                        modifier = Modifier.padding(20.dp),
                        style = button_medium
                    )
                }
                else -> {
                    News(newsList = categoryNewsList)
                }
            }
        }
    }
}








//@Preview(showBackground = true)
//@Composable
//fun NewsScreenPreview() {
//    NewsScreen(
//        navController = rememberNavController(),
//    )
//}

