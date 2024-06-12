package com.nohjason.minari.screens.profile

import android.net.Uri
import android.provider.UserDictionary.Words
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.size.Scale

@Preview
@Composable
fun ProfileScreen() {
    Column (modifier = Modifier.fillMaxSize()){
        Profile_box()
        Text(text ="포인트")
        Text(text = "1444")
        Contents_box()
        Shop_box()
        MyWord_box()
    }
}

@Composable
fun Profile_box(){
    var imageUri by remember { mutableStateOf("") }
    val launcher = rememberLauncherForActivityResult(contract =
    ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUri = uri.toString()
    }
    Column (){
        Button(onClick = { /*TODO*/ }) {
            Text(text = "로그아웃")
        }
        Row {
            Text(text = "별명")
            Text(text = "이름")
            Text(text = "메일")
            Text(text = "선택 카테고리")
            Canvas(modifier = Modifier) {

            }
            Image(
                painter = rememberImagePainter(
                    data = Uri.parse(imageUri),
                    builder = {scale(Scale.FIT)}
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.FillWidth,
            )
            IconButton(onClick = {
                launcher.launch("image/*")
            }) {
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = "Search Profile",
                )
            }
        }
    }
}

@Composable
fun Contents_box(){
    Box(modifier = Modifier){
        Row {
            Column {
                IconButton(onClick = { /*TODO*/ }) {
                    
                }
                Text(text = "단어장")
            }
            Column {
                IconButton(onClick = { /*TODO*/ }) {

                }
                Text(text = "상점")
            }
            Column {
                IconButton(onClick = { /*TODO*/ }) {

                }
                Text(text = "내역")
            }
        }
    }
}

@Composable
fun Shop_box(){
    Text(text = "아직 개발 중")
}

@Composable
fun MyWord_box(){
    Text(text = "아직 개발 중")
}