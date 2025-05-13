package com.nohjason.minari.screens.rout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.nohjason.minari.R
import com.nohjason.minari.navigation.Screens
import com.nohjason.minari.network.response.rout.GpsData
import com.nohjason.minari.ui.theme.MinariBlue
import com.nohjason.minari.ui.theme.MinariBlue300
import com.nohjason.minari.ui.theme.MinariGray100
import com.nohjason.minari.ui.theme.MinariGray400
import com.nohjason.minari.ui.theme.MinariGray600
import com.nohjason.minari.ui.theme.MinariGray900
import com.nohjason.minari.ui.theme.b2_medium
import com.nohjason.minari.ui.theme.button_bold
import com.nohjason.minari.ui.theme.button_medium
import com.nohjason.minari.ui.theme.h3_bold
import com.nohjason.minari.ui.theme.pretendard_bold
import com.nohjason.minari.ui.theme.pretendard_medium


@OptIn(ExperimentalPagerApi::class)
@Composable
fun StudentGrapesPager(
    grapeList: List<GpsData>,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState()

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        HorizontalPager(
            count = grapeList.size,
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            val item = grapeList[page]

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate("${Screens.Grapes.rout}/${item.gpsId}")
                    }
            ) {
                StudentGrapesCard(item = item)
            }
        }

        // 페이지 인디케이터
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(grapeList.size) { index ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(8.dp)
                        .background(
                            color = if (index == pagerState.currentPage) MinariBlue else Color.LightGray,
                            shape = CircleShape
                        )
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun StudentGrapesCard(item: GpsData) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .border(width = 1.dp, color = MinariGray100, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            // 제목과 시간 (왼쪽 정렬 기본)
            Text(
                text = item.gpsName,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF222222) // 진한 회색
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_clock),
                    contentDescription = "소요 시간",
                    tint = Color(0xFF222222),
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${item.gpsTime}분",
                    fontSize = 14.sp,
                    color = Color(0xFF666666)
                )
            }

//            Spacer(modifier = Modifier.height(12.dp))

            // 이미지만 오른쪽 벽에 붙이기
            Image(
                painter = painterResource(id = R.drawable.img_grapes),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .align(Alignment.End)
                    .padding(top = 8.dp)
                    .wrapContentWidth(Alignment.End)
            )

//            Spacer(modifier = Modifier.height(12.dp))

            // 해시태그 (왼쪽 정렬 기본)
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                listOf(
                    "#${item.gpsAgeGroup}",
                    "#${item.gpsWork}",
                    "#경제제도"
                ).forEach { tag ->
                    Text(
                        text = tag,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF5A8CFF), // 연한 파랑
                        modifier = Modifier
                            .padding(end = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 내용 요약 (왼쪽 정렬 기본)
            Text(
                text = item.gpsContent,
                fontSize = 13.sp,
                color = Color(0xFFB0B0B0), // 연한 회색
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}





@Preview(showBackground = true)
@Composable
fun PreviewStudentGrapesCard() {
    val dummyGpsData = GpsData(
        gpsId = 1,
        gpsName = "돈이 움직이는 세상",
        gpsContent = "금융과 경제의 흐름을 이해하는 튜토리얼입니다.",
        gpsTime = 15,
        gpsLike = false,
//        gpTpList = listOf("BEGINNER"),
        gpsAgeGroup = "20대",
        gpsWork = "학생"
    )
    StudentGrapesCard(item = dummyGpsData)
}

@Preview(showBackground = true)
@Composable
fun PreviewStudentGrapesPager() {
    val dummyList = listOf(
        GpsData(
            gpsId = 1,
            gpsName = "돈이 움직이는 세상",
            gpsContent = "금융과 경제의 흐름을 이해하는 튜토리얼입니다.",
            gpsTime = 15,
            gpsLike = false,
//            gpTpList = listOf("BEGINNER"),
            gpsAgeGroup = "20대",
            gpsWork = "학생"
        ),
        GpsData(
            gpsId = 2,
            gpsName = "투자의 기초",
            gpsContent = "투자에 대해 쉽게 배우는 가이드입니다.",
            gpsTime = 20,
            gpsLike = true,
//            gpTpList = listOf("INTERMEDIATE"),
            gpsAgeGroup = "30대",
            gpsWork = "직장인"
        )
    )
    val navController = rememberNavController()
    StudentGrapesPager(grapeList = dummyList, navController = navController)
}
