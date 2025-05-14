package com.nohjason.cheongfordo.screens.home

import android.content.Context
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.lazy.items
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.nohjason.cheongfordo.network.response.GetAllTermsResponse
import com.nohjason.cheongfordo.network.response.Term
import com.nohjason.cheongfordo.screens.home.todayterm.TodayTerm
import com.nohjason.cheongfordo.screens.home.todayterm.getRandomItems
import com.nohjason.cheongfordo.screens.home.todayterm.loadRandomItems
import com.nohjason.cheongfordo.screens.home.todayterm.saveRandomItems
import com.nohjason.cheongfordo.screens.profile.profile_data.ProfileViewModel
import com.nohjason.cheongfordo.screens.ui.titlebar.SearchBar
import com.nohjason.cheongfordo.ui.theme.MinariBlue100
import com.nohjason.cheongfordo.ui.theme.MinariBlue800
import com.nohjason.cheongfordo.ui.theme.caption_bold
import com.nohjason.minari.screens.rout.GrapeViewModel
import com.nohjason.myapplication.network.MainViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.compose.foundation.shape.CircleShape as CircleShape1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    var text by remember { mutableStateOf("") }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    var backPressedTime by rememberSaveable { mutableStateOf(0L) }
    var selectedCategory by remember { mutableStateOf("finance") }
    val getAllTerms by mainViewModel.getAllTerms.collectAsState()

    val context = LocalContext.current
    val preferencesManager = remember { PreferencesManager(context) }

//    val refreshResult by loginViewModel.refreshResult.collectAsState()
//    val refreshErrorCode by loginViewModel.refreshErrorCode.collectAsState()
//    val isLoggedIn by loginViewModel.isLoggedIn.collectAsState()

    BackHandler(onBack = {
        val currentTime = System.currentTimeMillis()
        if (currentTime - backPressedTime < 2000) {
            (context as ComponentActivity).finish()
        } else {
            Toast.makeText(context, "한 번 더 누르면 앱을 끌 수 있어요'", Toast.LENGTH_SHORT).show()
            backPressedTime = currentTime
        }
    })

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
                navController.navigate(Screens.ChatScreen.rout)
            }
        },
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White,
                ),
                title = {
                    SearchBar(navController = navController)
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
//            val randomItems = getRandomItems(context, getAllTerms!!)
            item {
                Text(
                    text = "오늘의 경제 단어",
                    style = b2_bold,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
                )

                // WordCard 스크롤 뷰
//                val randomItems = getRandomItems(context, getAllTerms!!)
//                items(randomItems) { item ->
//                    TodayTerm(navController, item = item)
//                }
                WordCardPager(
                    wordCardDataList = wordCardDataList,
                    navController = navController
                )

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

fun getRandomItems(context: Context, allTerms: GetAllTermsResponse): List<Term> {
    val sharedPref = context.getSharedPreferences("random_items_prefs", Context.MODE_PRIVATE)
    val lastSavedDate = sharedPref.getString("last_saved_date", "") ?: ""
    val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

    return if (currentDate != lastSavedDate) {
        // 날짜가 다르면 새로운 랜덤 리스트 생성
        val randomItems = allTerms.data.shuffled().take(5)
        saveRandomItems(context, randomItems, currentDate)
        randomItems
    } else {
        // 날짜가 같으면 저장된 리스트 불러오기
        loadRandomItems(context)
    }
}



//@Preview
//@Composable
//fun PreHome() {
//    HomeScreen(navController = rememberNavController(),)
//}
