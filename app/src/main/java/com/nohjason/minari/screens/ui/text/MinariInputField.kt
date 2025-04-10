package com.nohjason.minari.screens.ui.text

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nohjason.minari.ui.theme.MinariGray300
import com.nohjason.minari.ui.theme.MinariGray500
import com.nohjason.minari.ui.theme.MinariGray800
import com.nohjason.minari.ui.theme.b2_bold
import com.nohjason.minari.ui.theme.b2_medium

@Composable
fun MinariInputField(
    icon: Painter?, // 아이콘을 리소스 ID로 받음 (null 허용)
    label: String,
    onValueChange: (String) -> Unit = { }, // 기본값을 빈 람다로 설정
    onClickAction: () -> Unit = {}, // 기본값을 빈 람다로 설정
    isPassword: Boolean = false // 비밀번호 필드 여부
) {
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = {
            text = it
            onValueChange(it) // 외부로 값을 전달
        },
        placeholder = {
            Text(label, color = MinariGray300, style = b2_medium)
        },
        textStyle =
            if (text.isEmpty()) b2_medium.copy(color = MinariGray300)
            else b2_bold.copy(color = MinariGray800),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .background(Color(0xFFF8F8F8), RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        trailingIcon = {
            if (icon != null) {
                Icon(
                    painter = icon,
                    contentDescription = "icon",
                    tint = MinariGray500,
                    modifier = Modifier
                        .padding(12.dp)
                        .clickable { onClickAction() }
                )
            }
        },
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = MinariGray800
        )
    )
}



//@Preview(showBackground = true)
//@Composable
//fun PreInputField() {
//    Column(
//        modifier = Modifier
//            .background(Color.White)
//            .fillMaxSize()
//    ) {
//        MinariInputField(
//            icon = null,
//            label = "무엇이든 물어봐주세요!",
//            onClickAction = { println("첫 번째 입력창 클릭됨") }
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//        MinariInputField(
//            icon = android.R.drawable.ic_dialog_map,
//            label = "검색어를 입력하세요",
//            onClickAction = { println("아이콘 클릭됨!") }
//        )
//    }
//}
