package com.nohjason.minari.screens.dictionary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nohjason.minari.screens.dictionary.button.CatagoryButton
import com.nohjason.minari.screens.dictionary.button.DictionaryButton
import com.nohjason.minari.screens.dictionary.card.LikeWord
import com.nohjason.minari.screens.dictionary.card.WordCard
import com.nohjason.minari.screens.ui.text.MinariTextField
import com.nohjason.minari.ui.theme.MinariWhite

@Composable
fun DictionaryScreen() {
    var text by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(15.dp))

        MinariTextField(
            value = text,
            onValueChange = {text = it},
            onClick = {}
        )

        Spacer(modifier = Modifier.height(15.dp))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MinariWhite)
                .padding(10.dp)
        ) {
            Column {
                CatagoryButton()
                Spacer(modifier = Modifier.height(10.dp))
                LikeWord()
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestDictionaryScreen() {
    DictionaryScreen()
}