package com.nohjason.minari.screens.login.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nohjason.minari.R
import com.nohjason.minari.navigation.bottombar.BottomScreen
import com.nohjason.minari.screens.login.Screens
import com.nohjason.minari.screens.ui.text.MinariText
import com.nohjason.minari.ui.theme.MinariBlue
import com.nohjason.minari.ui.theme.pretendard_bold
import com.nohjason.minari.ui.theme.pretendard_medium
import com.nohjason.minari.ui.theme.rixfont

@Composable
fun LoginScreen(
    navController: NavHostController,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.weight(0.3f))
        Row {
            Column {
                Icon(
                    painter = painterResource(id = R.drawable.grape),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(60.dp)
                )
                Spacer(modifier = Modifier.height(7.dp))
            }
            MinariText(
                text = "청포도",
                color = MinariBlue,
                fontFamily = rixfont,
                size = 40,
                modifier = Modifier.align(Alignment.Bottom)
            )
        }
        Text(text = "청소년을 위한 포켓 경제 도우미", fontSize = 17.sp, fontFamily = pretendard_medium)


        Image(
            painter = painterResource(id = R.drawable.saly_10),
            contentDescription = null,
            modifier = Modifier.size(350.dp)
        )

        Spacer(modifier = Modifier.weight(0.1f))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 35.dp)
                .clip(CircleShape)
                .background(MinariBlue)
                .clickable { navController.navigate(Screens.Login.rout) }
        ) {
            Text(
                text = "로그인",
                color = Color.White,
                fontFamily = pretendard_bold,
                modifier = Modifier
                    .padding(13.dp)
                    .align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 35.dp)
                .clip(CircleShape)
                .background(Color.White)
                .clickable { navController.navigate(Screens.Signup.rout) }
                .border(1.dp, MinariBlue, shape = CircleShape),
        ) {
            Text(
                text = "회원가입",
                color = MinariBlue,
                fontFamily = pretendard_bold,
                modifier = Modifier
                    .padding(13.dp)
                    .align(Alignment.Center)

            )
        }

        IconButton(onClick = {
            navController.navigate(BottomScreen.Home.rout) {
                popUpTo(navController.graph.id) {
                    inclusive = true
                }
            }
        }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_radio_button_unchecked_24),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.weight(0.2f))
    }
}

@Preview(showSystemUi = true)
@Composable
fun Test() {
    LoginScreen(navController = rememberNavController())
}