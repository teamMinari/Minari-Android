package com.nohjason.cheongfordo.screens.ui.text

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.nohjason.cheongfordo.ui.theme.MinariGray300
import com.nohjason.cheongfordo.ui.theme.MinariGray500
import com.nohjason.cheongfordo.ui.theme.MinariGray800
import com.nohjason.cheongfordo.ui.theme.b2_bold
import com.nohjason.cheongfordo.ui.theme.b2_medium

@Composable
fun MinariInputField(
    modifier: Modifier = Modifier,
    icon: Painter?,
    label: String,
    onValueChange: (String) -> Unit = { },
    onClickAction: () -> Unit = {},
    isPassword: Boolean = false
) {
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = {
            text = it
            onValueChange(it)
        },
        placeholder = {
            Text(label, color = MinariGray300, style = b2_medium)
        },
        textStyle = if (text.isEmpty()) b2_medium.copy(color = MinariGray300)
        else b2_bold.copy(color = MinariGray800),
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFFF8F8F8), RoundedCornerShape(12.dp)), // modifier에 padding 제거
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
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { /* 키보드 완료 버튼 처리 */ }
        ),
        singleLine = true
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
