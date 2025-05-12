package com.nohjason.minari.screens.home

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nohjason.minari.R
import com.nohjason.minari.navigation.Screens
import com.nohjason.minari.navigation.bottombar.BottomScreen
import com.nohjason.minari.preferences.PreferencesManager
import com.nohjason.minari.screens.auth.viewmodel.LoginViewModel
import com.nohjason.minari.screens.home.data.HomeDummyData.list
import com.nohjason.minari.screens.home.data.HomeDummyData.wordCardDataList
import com.nohjason.minari.screens.home.ui.VerticalNewsList
import com.nohjason.minari.screens.home.ui.WordCardPager
import com.nohjason.minari.screens.ui.button.MinariButton
import com.nohjason.minari.screens.ui.button.NewsButton
import com.nohjason.minari.screens.ui.text.MinariInputField
import com.nohjason.minari.ui.theme.MinariBlue500
import com.nohjason.minari.ui.theme.MinariGray500
import com.nohjason.minari.ui.theme.MinariWhite
import com.nohjason.minari.ui.theme.b2_bold
import androidx.compose.ui.platform.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = viewModel()
) {
    var selectedCategory by remember { mutableStateOf("finance") }

    var text by remember { mutableStateOf("") }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    var backPressedTime by rememberSaveable { mutableStateOf(0L) }

    val context = LocalContext.current
    val preferencesManager = remember { PreferencesManager(context) }
    val refreshResult by loginViewModel.refreshResult.collectAsState()

    LaunchedEffect(Unit) {
        if (preferencesManager.isAutoLogin()) {
            val refreshToken = preferencesManager.getRefreshToken()
            if (!refreshToken.isNullOrEmpty()) {
                loginViewModel.refreshToken(refreshToken)
            } else {
                // 리프레시 토큰 없으면 로그인 화면으로
                navController.navigate(Screens.FirstScreen.rout) {
                    popUpTo(0)
                }
            }
        } else {
            // 자동로그인 설정 안됨 → 로그인 화면으로
            navController.navigate(Screens.FirstScreen.rout) {
                popUpTo(0)
            }
        }
    }

    BackHandler(onBack = {
        val currentTime = System.currentTimeMillis()
        if (currentTime - backPressedTime < 2000) {
            (context as ComponentActivity).finish()
        } else {
            Toast.makeText(context, "Press back again to exit", Toast.LENGTH_SHORT).show()
            backPressedTime = currentTime
        }
    })

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White,
                ),
                modifier = Modifier
                    .clip(RoundedCornerShape(0.dp, 0.dp, 20.dp, 20.dp))
                    .fillMaxWidth()
                    .height(125.dp),
                title = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(22.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.grape), // 로고 아이콘 추가
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            MinariInputField(
                                icon = painterResource(id = R.drawable.ic_search),
                                label = "검색",
                                onValueChange = { text = it },
                                onClickAction = { /* 검색 동작 */ },
                                isPassword = false,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        }
    ) { innerPadding ->
        val newsCategories = listOf(
            Triple(R.drawable.img_finance, "금융", "finance"),
            Triple(R.drawable.img_certificate, "증권", "stock"),
            Triple(R.drawable.img_property, "산업/재계", "industry"),
            Triple(R.drawable.img_real_estate, "부동산", "property"),
            Triple(R.drawable.img_global_economy, "글로벌\n경제", "global")
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F6FA))
                .padding(innerPadding),
        ) {
            // 1. 뉴스 태그 검색 섹션
            item {
                Text(
                    text = "뉴스 태그 검색",
                    style = b2_bold,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)
                )

                // 카테고리 버튼 행
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    newsCategories.forEach { (iconResId, text, category) ->
                        val isSelected = selectedCategory == category
                        NewsButton(
                            icon = painterResource(id = iconResId),
                            text = text,
                            isSelected = false,
                            onClick = { navController.navigate(BottomScreen.News.rout) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
            }


            // 2. 오늘의 경제 단어 섹션
            item {
                Text(
                    text = "오늘의 경제 단어",
                    style = b2_bold,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
                )

                // WordCard 스크롤 뷰
                WordCardPager(wordCardDataList = wordCardDataList, navController = navController)

                Spacer(modifier = Modifier.height(10.dp))
            }

            // 4. 뉴스 목록
            item {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "추천 뉴스",
                    style = b2_bold,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
                )
                VerticalNewsList(newsList = list)
            }

            item {
                MinariButton(
                    text = "많이 본 뉴스 더보기",
                    size = "large",
                    textColor = MinariGray500,
                    buttonColor = MinariWhite,
                    icon = painterResource(id = R.drawable.ic_star),
                    line = true,
                    enabled = true,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp)
                ) { }
                Spacer(modifier = Modifier.height(83.dp))
            }
        }
    }
}


@Preview
@Composable
fun PreHome() {
    HomeScreen(navController = rememberNavController(),)
}
