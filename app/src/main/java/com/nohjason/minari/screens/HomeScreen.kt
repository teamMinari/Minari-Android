package com.nohjason.minari.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen() {
    Column {
        Text(text = "This is Main Screen")
        Button(onClick = {
//            navController.navigate("profile")
        }) {
            Text(text = "go to Profile")
        }
    }
}