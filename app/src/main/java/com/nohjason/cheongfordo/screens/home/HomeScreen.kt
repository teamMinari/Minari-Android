package com.nohjason.cheongfordo.screens.home

import androidx.compose.foundation.Image
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
import androidx.compose.material.Icon
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.nohjason.cheongfordo.R
import com.nohjason.cheongfordo.navigation.Screens
import com.nohjason.cheongfordo.navigation.bottombar.BottomScreen
import com.nohjason.cheongfordo.preferences.PreferencesManager
import com.nohjason.cheongfordo.screens.auth.viewmodel.LoginViewModel
import com.nohjason.cheongfordo.screens.home.data.HomeDummyData.list
import com.nohjason.cheongfordo.screens.home.data.HomeDummyData.wordCardDataList
import com.nohjason.cheongfordo.screens.home.ui.VerticalNewsList
import com.nohjason.cheongfordo.screens.home.ui.WordCardPager
import com.nohjason.cheongfordo.screens.ui.button.MinariButton
import com.nohjason.cheongfordo.screens.ui.button.NewsButton
import com.nohjason.cheongfordo.screens.ui.text.MinariInputField
import com.nohjason.cheongfordo.ui.theme.MinariGray500
import com.nohjason.cheongfordo.ui.theme.MinariWhite
import com.nohjason.cheongfordo.ui.theme.b2_bold
import androidx.compose.ui.platform.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.nohjason.cheongfordo.screens.rout.GrapeViewModel
import com.nohjason.cheongfordo.ui.theme.MinariBlue100
import com.nohjason.cheongfordo.ui.theme.MinariBlue800
import com.nohjason.cheongfordo.ui.theme.caption_bold
import androidx.compose.foundation.shape.CircleShape as CircleShape1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: GrapeViewModel = hiltViewModel(),
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    var text by remember { mutableStateOf("") }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    var backPressedTime by rememberSaveable { mutableStateOf(0L) }
    var selectedCategory by remember { mutableStateOf("finance") }

    val context = LocalContext.current
    val preferencesManager = remember { PreferencesManager(context) }

//    val refreshResult by loginViewModel.refreshResult.collectAsState()
//    val refreshErrorCode by loginViewModel.refreshErrorCode.collectAsState()
//    val isLoggedIn by loginViewModel.isLoggedIn.collectAsState()

    // 자동 로그인 시도
    LaunchedEffect(Unit) {
        if (preferencesManager.isAutoLogin()) {
            val refreshToken = preferencesManager.getRefreshToken()
            if (!refreshToken.isNullOrEmpty()) {
                loginViewModel.refreshToken(refreshToken)
            } else {
                navController.navigate(Screens.FirstScreen.rout) {
                    popUpTo(0) { inclusive = true }
                }
            }
        } else {
            navController.navigate(Screens.FirstScreen.rout) {
                popUpTo(0) { inclusive = true }
            }
        }
    }



    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        floatingActionButton = {
            AiChatbotFab {
                // 버튼 클릭 시 동작
                navController.navigate(Screens.ChatScreen.rout)
            }
        },
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
                                onClickAction = {
                                    val token = preferencesManager.getToken().toString()
                                    viewModel.getTerm(termNm = text)
                                    navController.navigate(Screens.Term.rout + "/${text}")
                                },
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
                ) {
                    navController.navigate(BottomScreen.News.rout)
                }
                Spacer(modifier = Modifier.height(83.dp))
            }
        }
    }
}

@Composable
fun AiChatbotFab(
    onClick: () -> Unit
) {
    // 연한 파란색 배경(이미지처럼)
    val backgroundColor = MinariBlue100 // 이미지 배경색에 맞게 조정

    Surface(
        modifier = Modifier
            .size(80.dp),
        shape = CircleShape1,
        color = backgroundColor,
        shadowElevation = 4.dp,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_message), // 말풍선 아이콘
                contentDescription = "AI 챗봇",
                tint = MinariBlue800, // 진한 파란색
                modifier = Modifier.size(30.dp)
            )
            Text(
                text = "AI 챗봇",
                color = MinariBlue800,
                style = caption_bold
            )
        }
    }
}



@Preview
@Composable
fun PreHome() {
    HomeScreen(navController = rememberNavController(),)
}
