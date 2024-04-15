package com.nohjason.minari.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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
import com.nohjason.minari.screens.ui.News
import com.nohjason.minari.screens.ui.RecommendWords
import com.nohjason.minari.screens.ui.button.NewsCategoryButton
import com.nohjason.minari.screens.ui.text.MinariText
import com.nohjason.minari.ui.theme.MinariWhite

@Composable
fun HomeScreen() {
    var text by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize(),
//            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.grape),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(35.dp)
            )
            MinariText(text = "청For도", size = 20)
            Spacer(modifier = Modifier.fillMaxWidth())
        }

        BasicTextField(
            value = text,
            onValueChange = { text = it },
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .height(25.dp)
                        .padding(horizontal = 30.dp)
                        .fillMaxWidth()
                        .background(MinariWhite, shape = CircleShape),
//                        .padding(all = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    innerTextField()
                    Spacer(modifier = Modifier.weight(0.1f))
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "",
                        modifier = Modifier
                            .size(25.dp)
                            .padding(end = 10.dp),
                        tint = Color.DarkGray,
                    )
                }
            },
        )
        Box(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxSize()
                .background(MinariWhite),
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RecommendWords()

                Spacer(modifier = Modifier.height(20.dp))

                Row {
                    NewsCategoryButton()
                    Spacer(modifier = Modifier.width(5.dp))
                    NewsCategoryButton()
                    Spacer(modifier = Modifier.width(5.dp))
                    NewsCategoryButton()
                    Spacer(modifier = Modifier.width(5.dp))
                    NewsCategoryButton()
                }

                Spacer(modifier = Modifier.height(20.dp))

                News()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    HomeScreen()
}