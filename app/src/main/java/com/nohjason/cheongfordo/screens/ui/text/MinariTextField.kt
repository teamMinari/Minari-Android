package com.nohjason.cheongfordo.screens.ui.text

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.nohjason.cheongfordo.screens.home.HomeScreen
import com.nohjason.cheongfordo.ui.theme.MinariBlue
import com.nohjason.cheongfordo.ui.theme.MinariWhite

//@Composable
//fun MinariTextField(
//    modifier: Modifier = Modifier,
//    value: String,
//    onValueChange: (String) -> Unit,
//    onClick: () -> Unit,
//    hint: String = "검색어 입력",
//    fontSize: TextUnit = 15.sp,
//) {
//    val keyboardController = LocalSoftwareKeyboardController.current
//    var isFocused by remember { mutableStateOf(false) }
//    val focusRequester = remember { FocusRequester() }
//
//    BasicTextField(
//        value = value,
//        onValueChange = onValueChange,
//        modifier = modifier
//            .focusRequester(focusRequester)
//            .onFocusChanged { focusState ->
//                isFocused = focusState.isFocused
//            },
//        textStyle = TextStyle(
//            fontSize = fontSize,
//            color = Color.Black
//        ),
//        keyboardOptions = KeyboardOptions.Default.copy(
//            imeAction = ImeAction.Search
//        ),
//        keyboardActions = KeyboardActions(
//            onSearch = {
//                keyboardController?.hide()
//                onClick()
//            }
//        ),
//        maxLines = 1,
//        singleLine = true,
//        decorationBox = { innerTextField ->
//            Row(
//                modifier = Modifier
//                    .background(MinariWhite, shape = CircleShape)
//                    .padding(horizontal = 20.dp, vertical = 7.dp)
//                    .then(modifier),
//                verticalAlignment = Alignment.CenterVertically,
//            ) {
//                if (value.isEmpty() && !isFocused) {
//                    Text(
//                        text = hint,
//                        color = Color(0xFF8C8C8C),
//                        fontSize = fontSize,
//                        modifier = Modifier.weight(1f)
//                    )
//                } else {
//                    Box(modifier = Modifier.weight(1f)) {
//                        innerTextField()
//                    }
//                }
//
//                Icon(
//                    imageVector = Icons.Default.Search,
//                    contentDescription = "Search Icon",
//                    modifier = Modifier
//                        .size(30.dp)
//                        .clickable { onClick() },
//                    tint = MinariBlue
//                )
//            }
//        }
//    )
//}
