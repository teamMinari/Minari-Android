package com.nohjason.minari.screens.auth.ui.register_screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nohjason.minari.R
import com.nohjason.minari.ui.theme.MinariGray500
import com.nohjason.minari.ui.theme.MinariWhite
import com.nohjason.minari.ui.theme.h4_bold
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nohjason.minari.navigation.Screens
import com.nohjason.minari.navigation.bottombar.BottomScreen
import com.nohjason.minari.screens.auth.viewmodel.RegisterViewModel
import com.nohjason.minari.ui.theme.MinariGray200
import com.nohjason.minari.ui.theme.MinariGray800
import com.nohjason.minari.ui.theme.b2_bold
import com.nohjason.minari.ui.theme.b2_medium


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SelectJobScreen(
    navController: NavController,
    registerViewModel: RegisterViewModel = hiltViewModel()
) {
    val registerResponse by registerViewModel.registerResponse.collectAsState()
    LaunchedEffect(registerResponse) {
        if (registerResponse != null ) {
            navController.navigate(Screens.Login.rout)
        }
    }

    val jobOptions = listOf(
        "문주호" to R.drawable.grape,
        "김호준" to R.drawable.grape,
        "조성걸" to R.drawable.grape,
        "김수아" to R.drawable.grape
    )

    val selectedJob = remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .imePadding(),
        horizontalAlignment = Alignment.Start
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "뒤로가기",
            modifier = Modifier
                .padding(top = 17.dp)
                .clickable { /* 뒤로가기 이벤트 */ }
        )

        Spacer(modifier = Modifier.height(44.dp))

        Text(
            text = "원하는 직업을\n선택해 주세요.",
            style = h4_bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxWidth()
            ) {
                JobButton(
                    text = jobOptions[0].first,
                    imgRes = jobOptions[0].second,
                    isSelected = selectedJob.value == jobOptions[0].first
                ) {
                    registerViewModel.register()
                    selectedJob.value = if (selectedJob.value == jobOptions[0].first) null else jobOptions[0].first
                }
                JobButton(
                    text = jobOptions[1].first,
                    imgRes = jobOptions[1].second,
                    isSelected = selectedJob.value == jobOptions[1].first
                ) {
                    registerViewModel.register()
                    selectedJob.value = if (selectedJob.value == jobOptions[1].first) null else jobOptions[1].first
                }
            }
            Row(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                JobButton(
                    text = jobOptions[2].first,
                    imgRes = jobOptions[2].second,
                    isSelected = selectedJob.value == jobOptions[2].first
                ) {
                    registerViewModel.register()
                    selectedJob.value = if (selectedJob.value == jobOptions[2].first) null else jobOptions[2].first
                }
                JobButton(
                    text = jobOptions[3].first,
                    imgRes = jobOptions[3].second,
                    isSelected = selectedJob.value == jobOptions[3].first
                ) {
                    registerViewModel.register()
                    selectedJob.value = if (selectedJob.value == jobOptions[3].first) null else jobOptions[3].first
                }
            }
        }
    }
}

@Composable
fun JobButton(
    text: String,
    imgRes: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val textColor = if (isSelected) MinariGray800 else MinariGray800.copy(alpha = 0.6f)
    val borderColor = if (isSelected) MinariGray200 else MinariGray200.copy(alpha = 0.6f)
    val imageAlpha = if (isSelected) 1f else 0.6f

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MinariWhite,
            contentColor = textColor,
        ),
        border = BorderStroke(
            width = 1.dp,
            color = borderColor
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .width(152.dp)
            .height(216.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = imgRes),
                contentDescription = "직업 이미지",
                modifier = Modifier
                    .size(80.dp)
                    .alpha(imageAlpha)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = text,
                style = b2_bold
            )
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun PreSelectJobScreen(){
//    SelectJobScreen()
//}