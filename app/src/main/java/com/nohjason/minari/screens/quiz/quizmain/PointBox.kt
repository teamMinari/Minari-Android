package com.nohjason.minari.screens.quiz.quizmain

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nohjason.minari.R
import com.nohjason.minari.ui.theme.MinariBlue400
import com.nohjason.minari.ui.theme.MinariGray100
import com.nohjason.minari.ui.theme.MinariGray200
import com.nohjason.minari.ui.theme.MinariGray500
import com.nohjason.minari.ui.theme.MinariGray900
import com.nohjason.minari.ui.theme.b2_bold
import com.nohjason.minari.ui.theme.h1_bold

@Composable
fun PointBox(
    point: Int,
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(125.dp)
            .background(
                color = MinariGray100,
                shape = RoundedCornerShape(14.dp)
            )
    ) {
        // 텍스트 컬럼 (좌상단)
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 20.dp, top = 24.dp)
        ) {
            Text(
                text = "%,d P".format(point),
                color = MinariGray900,
                style = h1_bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "소비하러가기>",
                color = MinariBlue400,
                style = b2_bold,
                modifier = Modifier.clickable {
                    navController.navigate("webview?url=${Uri.encode("http://pf.kakao.com/_xiiQZn")}")
                }
            )
        }
        // 흐린 아이콘 (우하단, 박스보다 크게, alpha 적용)
        Icon(
            painter = painterResource(id = R.drawable.ic_basket),
            contentDescription = "장바구니",
            tint = MinariGray200,
            modifier = Modifier
                .offset(x = 20.dp, y = 20.dp)
                .size(200.dp)
                .align(Alignment.BottomEnd)
        )
    }
}





@Preview
@Composable
fun PrePointBox() {
    PointBox(point = 1002, navController = rememberNavController())
}