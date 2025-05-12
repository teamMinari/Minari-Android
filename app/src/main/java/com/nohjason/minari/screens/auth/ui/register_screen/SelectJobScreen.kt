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
import androidx.navigation.NavController
import com.nohjason.minari.navigation.Screens
import com.nohjason.minari.navigation.bottombar.BottomScreen
import com.nohjason.minari.screens.auth.viewmodel.RegisterViewModel
import com.nohjason.minari.ui.theme.MinariGray200
import com.nohjason.minari.ui.theme.MinariGray800
import com.nohjason.minari.ui.theme.b2_bold
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.*
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SelectJobScreen(
    navController: NavController? = null,
    registerViewModel: RegisterViewModel = hiltViewModel()
) {
    val registerResponse by registerViewModel?.registerResponse?.collectAsState() ?: remember { mutableStateOf(null) }
    LaunchedEffect(registerResponse) {
        if (registerResponse != null) {
            navController?.navigate(Screens.Login.rout)
        }
    }

    val jobOptions = listOf(
        "문주호" to R.drawable.grape,
        "김호준" to R.drawable.grape,
        "조성걸" to R.drawable.grape,
        "김수아" to R.drawable.grape
    )

    val selectedJob = remember { mutableStateOf<String?>(null) }

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    fun widthRatio(ratio: Float) = screenWidth * ratio
    fun heightRatio(ratio: Float) = screenHeight * ratio

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = widthRatio(24f / 360f))
            .imePadding(),
        horizontalAlignment = Alignment.Start
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "뒤로가기",
            modifier = Modifier
                .padding(top = heightRatio(17f / 640f))
                .clickable { /* 뒤로가기 이벤트 */ }
        )

        Spacer(modifier = Modifier.height(heightRatio(44f / 640f)))

        Text(
            text = "원하는 직업을\n선택해 주세요.",
            style = h4_bold
        )

        Spacer(modifier = Modifier.height(heightRatio(32f / 640f)))

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
                    registerViewModel?.register()
                    selectedJob.value = if (selectedJob.value == jobOptions[0].first) null else jobOptions[0].first
                }
                JobButton(
                    text = jobOptions[1].first,
                    imgRes = jobOptions[1].second,
                    isSelected = selectedJob.value == jobOptions[1].first
                ) {
                    registerViewModel?.register()
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
                    registerViewModel?.register()
                    selectedJob.value = if (selectedJob.value == jobOptions[2].first) null else jobOptions[2].first
                }
                JobButton(
                    text = jobOptions[3].first,
                    imgRes = jobOptions[3].second,
                    isSelected = selectedJob.value == jobOptions[3].first
                ) {
                    registerViewModel?.register()
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
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    fun widthRatio(ratio: Float) = screenWidth * ratio
    fun heightRatio(ratio: Float) = screenHeight * ratio

    val textColor = if (isSelected) MinariGray800 else MinariGray800.copy(alpha = 0.6f)
    val borderColor = if (isSelected) MinariGray200 else MinariGray200.copy(alpha = 0.6f)
    val imageAlpha = if (isSelected) 1f else 0.6f

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MinariWhite,
            contentColor = textColor
        ),
        border = BorderStroke(
            width = 1.dp,
            color = borderColor
        ),
        shape = RoundedCornerShape(widthRatio(16f / 360f)),
        modifier = Modifier
            .width(widthRatio(152f / 360f))
            .height(heightRatio(216f / 640f))
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
                    .size(widthRatio(80f / 360f))
                    .graphicsLayer { alpha = imageAlpha }
            )
            Spacer(modifier = Modifier.height(heightRatio(8f / 640f)))
            Text(
                text = text,
                style = b2_bold
            )
        }
    }
}

//@Preview(showBackground = true, widthDp = 360, heightDp = 640)
//@Composable
//fun SelectJobScreenPreview() {
//    SelectJobScreen()
//}