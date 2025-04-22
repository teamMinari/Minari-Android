package com.nohjason.minari.screens.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.nohjason.minari.R
import com.nohjason.minari.ui.theme.MinariBlue500
import com.nohjason.minari.ui.theme.MinariGray200
import com.nohjason.minari.ui.theme.MinariGray400
import com.nohjason.minari.ui.theme.MinariGray500
import com.nohjason.minari.ui.theme.MinariGray900
import com.nohjason.minari.ui.theme.MinariWhite
import com.nohjason.minari.ui.theme.b1_bold
import com.nohjason.minari.ui.theme.button_bold
import com.nohjason.minari.ui.theme.caption_medium

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WordCardPager(
    wordCardDataList: List<Triple<String, String, Int>>,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(color = MinariWhite)
            .padding(20.dp)
    ) {
        HorizontalPager(
            count = wordCardDataList.size,
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            val wordCardData = wordCardDataList[page]

            // 2. Box로 감싸서 클릭 영역 확장
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        // 3. 클릭 시 상세 화면으로 이동
                        navController.navigate("wordDetail/${wordCardData.third}") // third는 ID 값으로 가정
                    }
            ) {
                WordCard(
                    title = wordCardData.first,
                    description = wordCardData.second,
                )
            }
        }

        Spacer(Modifier.height(20.dp))

        // 페이지 인디케이터
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(wordCardDataList.size) { index ->
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
    }
}
//@OptIn(ExperimentalPagerApi::class)
//@Composable
//fun WordCardPager(
//    wordCardDataList: List<Triple<String, String, Int>>,
//    navController: NavHostController, // 추가된 파라미터
//    modifier: Modifier = Modifier
//) {
//    val pagerState = rememberPagerState()
//
//    Column (
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 20.dp)
//            .clip(RoundedCornerShape(16.dp))
//            .background(color = MinariWhite)
//            .padding(20.dp)
//            .clickable {
//                navController.navigate("wordDetail/${wordCardData.third}") // third는 ID 값으로 가정
//            }
//    ){
//        HorizontalPager(
//            count = wordCardDataList.size,
//            state = pagerState,
//            modifier = Modifier.fillMaxWidth()
//        ) { page ->
//            val wordCardData = wordCardDataList[page]
//            WordCard(
//                title = wordCardData.first,
//                description = wordCardData.second,
//            )
//        }
//
//        Spacer(Modifier.height(20.dp))
//
//        // 페이지 인디케이터
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 8.dp),
//            horizontalArrangement = Arrangement.Center
//        ) {
//            repeat(wordCardDataList.size) { index ->
//                Box(
//                    modifier = Modifier
//                        .padding(horizontal = 4.dp)
//                        .size(8.dp)
//                        .background(
//                            color = if (index == pagerState.currentPage) MinariBlue500.copy(alpha = 0.65f) else MinariGray200,
//                            shape = CircleShape
//                        )
//                )
//            }
//        }
//    }
//}




@Composable
fun WordCard(title: String, description: String) {
    var isExpanded by remember { mutableStateOf(false) }
    var isTextOverflowing by remember { mutableStateOf(false) }

    val displayMaxLines = if (isExpanded) Int.MAX_VALUE else 1

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = title,
                color = MinariGray900,
                style = b1_bold
            )
            Spacer(modifier = Modifier.width(10.dp))
            for (i in 1..2) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = Color.Unspecified
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = description,
                color = MinariGray500,
                style = button_bold,
                maxLines = displayMaxLines,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f),
                onTextLayout = { textLayoutResult ->
                    // 텍스트가 maxLines보다 더 많은 줄을 차지하는지 감지
                    if (!isExpanded) {
                        isTextOverflowing = textLayoutResult.lineCount > displayMaxLines
                    }
                }
            )

            if (isTextOverflowing && !isExpanded) {
                Text(
                    text = "더보기",
                    color = MinariGray400,
                    style = caption_medium,
                    modifier = Modifier
                        .clickable { isExpanded = true }
                        .padding(start = 8.dp)
                )
            }
        }
    }
}



data class WordCardData(
    val title: String,
    val description: String,
    val starIconId: Int
)