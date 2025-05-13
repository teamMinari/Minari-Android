package com.nohjason.minari.screens.rout

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nohjason.minari.R
import com.nohjason.minari.navigation.bottombar.BottomScreen
import com.nohjason.minari.network.response.rout.GpsData
import com.nohjason.minari.navigation.Screens
import com.nohjason.minari.screens.home.data.HomeDummyData.list
import com.nohjason.minari.screens.profile.profile_data.ProfileViewModel
import com.nohjason.minari.screens.rout.response.Gps
import com.nohjason.minari.ui.theme.MinariBlue
import com.nohjason.minari.ui.theme.MinariBlue400
import com.nohjason.minari.ui.theme.MinariBlue500
import com.nohjason.minari.ui.theme.MinariGradation
import com.nohjason.minari.ui.theme.MinariGray100
import com.nohjason.minari.ui.theme.MinariGray400
import com.nohjason.minari.ui.theme.MinariGray50
import com.nohjason.minari.ui.theme.MinariGray500
import com.nohjason.minari.ui.theme.MinariGray800
import com.nohjason.minari.ui.theme.MinariGray900
import com.nohjason.minari.ui.theme.MinariWhite
import com.nohjason.minari.ui.theme.b1_bold
import com.nohjason.minari.ui.theme.b2_bold
import com.nohjason.minari.ui.theme.b2_medium
import com.nohjason.minari.ui.theme.button_medium
import com.nohjason.minari.ui.theme.caption_medium
import com.nohjason.minari.ui.theme.h3_bold
import com.nohjason.minari.ui.theme.h4_bold
import com.nohjason.minari.ui.theme.pretendard_bold
import com.nohjason.minari.ui.theme.pretendard_extra_bold
import com.nohjason.minari.ui.theme.pretendard_medium
import com.nohjason.minari.ui.theme.pretendard_semibold

@Composable
fun Rout(
    navController: NavController,
    viewModel: GrapeViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val route by viewModel.route.collectAsState()
    val gpsState by viewModel.categoryRoute.collectAsState()
    val profile by profileViewModel.profileData.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getGpsByCategory(age = "TEENS", work = "MEMBEROFSOCIETY")
    }
    val context = LocalContext.current

    // 더미 데이터 (테스트용)
    val dummyData = remember {
        listOf(
            GpsData(
                gpsId = 1,
                gpsName = "학생들에게 맞는 경제",
                gpsContent = "학생들에게 맞춤 경제 내용을 확인하고 새로운 생각을 넓힐 수 있어요.",
                gpsTime = 27,
                gpsLike = false,
                gpsAgeGroup = "초급",
                gpsWork = "고등학생"
            ),
            GpsData(
                gpsId = 2,
                gpsName = "경제 제도 이해하기",
                gpsContent = "경제 제도의 기본 원리와 실제 적용 사례를 배워봅니다.",
                gpsTime = 35,
                gpsLike = true,
                gpsAgeGroup = "초급",
                gpsWork = "첫 시작"
            )
        )
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.getAllGps()
    }

    BackHandler(onBack = {
        navController.popBackStack(BottomScreen.Home.rout, inclusive = false)
    })

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        item {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                RoutTopSection()

                Box(
                    modifier = Modifier
                        .offset(y = 70.dp)
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 40.dp)
                        .clip(RoundedCornerShape(50))
                        .clickable {
                            Toast.makeText(context, "아직 준비 중인 기능입니다!", Toast.LENGTH_SHORT).show()
                        }
                        .height(55.dp)
                        .width(200.dp)
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFF363CD5),
                                    Color(0xFF701BDD)
                                ),
                                startX = -30f,
                                endX = Float.POSITIVE_INFINITY
                            )
                        ),
                    contentAlignment = Alignment.Center // Box 내부 컨텐츠 중앙 정렬
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        // padding은 Row 내부 좌우 공간 확보용
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_study),
                            tint = Color.Unspecified,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "학습하러 가기",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontFamily = pretendard_semibold,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }

            val studentGrapes = gpsState?.data ?: emptyList()
        item {
            Column (
                Modifier
                    .padding(horizontal = 20.dp)
            ){
                Spacer(modifier = Modifier.height(15.dp))
                
                Text(
                    text = "고등학생 추천 포도송이",
                    fontFamily = pretendard_bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 10.dp)
                )

                Spacer(modifier = Modifier.height(5.dp))

                if (studentGrapes.isNotEmpty()) {
                    StudentGrapesPager(
                        grapeList = studentGrapes,
                        navController = navController,
                        modifier = Modifier.fillMaxWidth()
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "아직 만드는 중인 포도송이입니다!",
                            fontSize = 16.sp,
                            fontFamily = pretendard_medium,
                            color = Color.Gray
                        )
                    }
                }
            }
        }


        // 최신 포도송이 섹션
        item {
            Column (
                Modifier
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "최신 포도송이",
                    fontFamily = pretendard_bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
        }

        // 최신 포도송이 리스트 (동일한 데이터 재사용)
        if (route?.data?.isNotEmpty() == true) {
            items(route!!.data.take(2)) { item ->
                Column (
                    Modifier
                        .padding(horizontal = 20.dp)
                ) {
                    GpsItem(
                        item = item,
                        onClick = { navController.navigate("${Screens.Grapes.rout}/${item.gpsId}") },
                        onLikeClick = { viewModel.likes("GRAPES", item.gpsId) }
                    )
                }
            }
        } else {
            items(dummyData.take(2)) { item ->
                Column (
                    Modifier
                        .padding(horizontal = 20.dp)
                ) {
                    GpsItem(
                        item = item,
                        onClick = { navController.navigate("${Screens.Grapes.rout}/${item.gpsId}") },
                        onLikeClick = { viewModel.likes("GRAPES", item.gpsId) }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GpsItem(
    item: GpsData,
    onClick: () -> Unit,
    onLikeClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .border(width = 1.dp, color = MinariGray100)
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
        ) {
            // 제목과 좋아요 버튼
            Text(
                text = item.gpsName,
                style = h4_bold,
                color = MinariGray900
            )


            Spacer(modifier = Modifier.height(5.dp))

            // 소요 시간
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_clock),
                    contentDescription = "소요 시간",
                    modifier = Modifier.size(16.dp),
                    tint = MinariGray900
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${item.gpsTime}분",
                    style = caption_medium,
                    color = Color(0xFF434343)
                )
            }

            Spacer(modifier = Modifier.height(5.dp))

            // 태그 리스트 (gpsAgeGroup과 gpsWork를 활용)
            val tags = listOf(
                "#${item.gpsAgeGroup}",
                "#${item.gpsWork}",
                "#경제제도"
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                items(tags) { tag ->
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .padding(horizontal = 5.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = tag,
                            color = MinariGray500,
                            style = button_medium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 내용
            Text(
                text = item.gpsContent,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = MinariGray400,
                style = b2_medium
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewGpsItem() {
    val dummyItem = GpsData(
        gpsId = 1,
        gpsName = "학생들에게 맞는 경제",
        gpsContent = "학생들에게 맞춤 경제 내용을 확인하고 새로운 생각을 넓힐 수 있어요.",
        gpsTime = 27,
        gpsLike = true,
        gpsAgeGroup = "초급",
        gpsWork = "고등학생"
    )

    GpsItem(
        item = dummyItem,
        onClick = {},
        onLikeClick = {}
    )
}

@Composable
fun RoutTopSection(
    modifier: Modifier = Modifier,
    onClickStart: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp))
            .background(Color.White)
            .fillMaxWidth()
            .border(width = 1.dp, color = Color(0xFFECEFFB))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
                .padding(horizontal = 20.dp)
        ) {
            // 튜토리얼 타이틀
            Row (
                verticalAlignment = Alignment.CenterVertically,
            ){
                Icon(
                    painter = painterResource(R.drawable.ic_map),
                    tint = Color.Unspecified,
                    contentDescription = "튜토리얼 아이콘",
                    modifier = Modifier
                        .size(25.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "튜토리얼",
                    style = h3_bold,
                    color = MinariBlue500
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "자신에게 맞는 포도송이를 획득할 수 있어요.",
                style = button_medium,
                color = MinariGray800
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // 원형 진행도 + 텍스트
            CircularProgressIndicator(
                percentage = 0.75f,
            )

            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "경제의 시작",
                    style = b1_bold,
                    color = MinariGray900
                )
                Text(
                    text = "학습중",
                    style = b2_bold,
                    color = MinariBlue400
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            // 캐릭터 이미지
            Image(
                modifier = Modifier
                    .size(120.dp),
                painter = painterResource(R.drawable.img_chareacters), // 실제 캐릭터 이미지로 교체
                contentDescription = null
            )
        }

        Box(
        ){
            WavyBackground()
        }

    }
}

@Composable
fun CircularProgressIndicator(
    percentage: Float,
) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.size(100.dp)) {
        CircularProgressIndicator(
            progress = percentage,
            strokeWidth = 4.dp,
            color = MinariBlue,
            modifier = Modifier.size(70.dp)
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "${(percentage * 100).toInt()}%",
                style = b2_bold,
                color = MinariBlue500
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
private fun WavyBackground(
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        ) {
            val width = size.width
            val height = size.height

            val waveY = height * 0.3f

            val path = Path().apply {
                moveTo(0f, waveY)
                cubicTo(
                    width * 0.25f, height * -0.8f,
                    width * 0.75f, height * 1.2f,
                    width, waveY
                )
                lineTo(width, height)
                lineTo(0f, height)
                close()
            }

            drawPath(path = path, color = Color(0xFFA7ACFA))
            drawRect(
                color = Color(0xFFA7ACFA).copy(alpha = 0.2f),
                topLeft = Offset(0f, waveY),
                size = androidx.compose.ui.geometry.Size(width, height - waveY)
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRoutTopSection() {
    RoutTopSection()
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun PreviewRoutScreen() {
//    RoutPreview()
//}


