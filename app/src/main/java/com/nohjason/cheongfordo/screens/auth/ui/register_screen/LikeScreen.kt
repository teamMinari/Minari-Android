package com.nohjason.cheongfordo.screens.auth.ui.register_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nohjason.cheongfordo.R
import com.nohjason.cheongfordo.screens.ui.button.MinariButton
import com.nohjason.cheongfordo.ui.theme.MinariBlue500
import com.nohjason.cheongfordo.ui.theme.MinariWhite
import com.nohjason.cheongfordo.ui.theme.h4_bold
import androidx.compose.runtime.mutableStateListOf
import androidx.navigation.NavController
import com.nohjason.cheongfordo.navigation.Screens
import com.nohjason.cheongfordo.ui.theme.MinariGray500
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.unit.*

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LikeScreen(
    navController: NavController? = null // Preview용 null 허용
) {
    var isButtonEnabled by remember { mutableStateOf(false) }
    val likeTagList = remember { mutableStateListOf<String>() }
    val tagOptions = listOf("10대", "20대", "30대", "40대", "50대", "60대", "70대", "80대")

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    fun heightRatio(ratio: Float) = screenHeight * ratio
    fun widthRatio(ratio: Float) = screenWidth * ratio

    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
        horizontalAlignment = Alignment.Start
    ) {
        Column(
            modifier = Modifier.padding(horizontal = widthRatio(24f / 360f))
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "뒤로가기",
                modifier = Modifier
                    .padding(top = heightRatio(17f / 640f))
                    .clickable { /* 뒤로가기 동작 추가 */ }
            )

            Spacer(modifier = Modifier.height(heightRatio(44f / 640f)))

            Text(
                text = "어떤 주제에 관심이\n있으신가요?",
                style = h4_bold
            )

            Spacer(modifier = Modifier.height(heightRatio(40f / 640f)))

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(widthRatio(8f / 360f)),
                verticalArrangement = Arrangement.spacedBy(heightRatio(8f / 640f))
            ) {
                tagOptions.forEach { tag ->
                    val isSelected = tag in likeTagList
                    MinariButton(
                        text = tag,
                        size = "Small",
                        textColor = if (isSelected) MinariGray500 else MinariGray500.copy(alpha = 0.6f),
                        buttonColor = if (isSelected) MinariWhite else MinariWhite.copy(alpha = 0.6f),
                        line = true,
                        enabled = true
                    ) {
                        if (isSelected) {
                            likeTagList.remove(tag)
                        } else {
                            likeTagList.add(tag)
                        }
                        isButtonEnabled = likeTagList.isNotEmpty()
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .imePadding(),
                contentAlignment = Alignment.Center
            ) {
                MinariButton(
                    text = "다음",
                    size = "Large",
                    textColor = MinariWhite,
                    buttonColor = MinariBlue500,
                    line = false,
                    enabled = isButtonEnabled,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        navController?.navigate(Screens.SelectJobScreen.rout)
                    }
                )
            }

            Spacer(modifier = Modifier.height(heightRatio(8f / 640f)))
        }
    }
}

//@Preview(showBackground = true, widthDp = 360, heightDp = 640)
//@Composable
//fun LikeScreenPreview() {
//    LikeScreen()
//}