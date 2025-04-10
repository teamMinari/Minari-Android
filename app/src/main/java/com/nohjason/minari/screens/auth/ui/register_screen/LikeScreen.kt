package com.nohjason.minari.screens.auth.ui.register_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nohjason.minari.R
import com.nohjason.minari.screens.ui.button.MinariButton
import com.nohjason.minari.ui.theme.MinariBlue500
import com.nohjason.minari.ui.theme.MinariWhite
import com.nohjason.minari.ui.theme.h4_bold
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nohjason.minari.navigation.Screens
import com.nohjason.minari.screens.auth.viewmodel.RegisterViewModel
import com.nohjason.minari.ui.theme.MinariGray200
import com.nohjason.minari.ui.theme.MinariGray500



@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LikeScreen(
    navController: NavController,
) {
    var isButtonEnabled by remember { mutableStateOf(false) }

    val likeTagList = remember { mutableStateListOf<String>() } // 선택된 태그 리스트

    val tagOptions = listOf("10대", "20대", "30대", "40대", "50대", "60대", "70대", "80대") // 태그 목록

    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
        horizontalAlignment = Alignment.Start
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "뒤로가기",
                modifier = Modifier
                    .padding(top = 17.dp)
                    .clickable { } // 뒤로가기 동작 추가 필요
            )

            Spacer(modifier = Modifier.height(44.dp))

            Text(
                text = "어떤 주제에 관심이\n있으신가요?",
                style = h4_bold
            )

            Spacer(modifier = Modifier.height(40.dp))

            // 태그 버튼 UI
            FlowRow{
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
                            likeTagList.remove(tag) // 이미 선택된 경우 해제
                        } else {
                            likeTagList.add(tag)
                        }
                        isButtonEnabled = likeTagList.isNotEmpty()
                    }
                }
            }


            Spacer(modifier = Modifier.weight(1f))

            // 다음 버튼
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
                        navController.navigate(Screens.SelectJobScreen.rout)
                    }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}











//@Preview
//@Composable
//fun PreLikeScreen(){
//    Column(
//        modifier = Modifier
//            .background(Color.White)
//            .fillMaxSize()
//    ){
//        LikeScreen()
//    }
//}