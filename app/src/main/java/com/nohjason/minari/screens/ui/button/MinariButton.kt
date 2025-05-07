package com.nohjason.minari.screens.ui.button

import android.graphics.drawable.Icon
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nohjason.minari.R
import com.nohjason.minari.ui.theme.MinariBlue500
import com.nohjason.minari.ui.theme.MinariGray200
import com.nohjason.minari.ui.theme.b2_bold
import com.nohjason.minari.ui.theme.button_bold
import com.nohjason.minari.ui.theme.caption_bold

@Composable
fun MinariButton(
    text: String, // 라벨 값
    size: String, // 사이즈 Large, Medium, Small로 나눠짐
    textColor: Color, // 라벨 텍스트 컬러
    buttonColor: Color, // 버튼 배경 컬러
    line: Boolean = false, // Stroke 선 여부
    enabled: Boolean = true, // 버튼 활성화 여부
    icon: Painter? = null,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val border = if (line) BorderStroke(1.dp, MinariGray200) else null

    Button(
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = buttonColor,
            disabledBackgroundColor = buttonColor.copy(alpha = 0.6f),
            contentColor = textColor,
            disabledContentColor = textColor.copy(alpha = 0.6f)
        ),
        border = border,
        elevation = ButtonDefaults.elevation(0.dp),
        modifier = modifier
            .padding(4.dp)
            .imePadding()
    ) {
        val textStyle = when (size) {
            "Large" -> b2_bold
            "Medium" -> button_bold
            else -> caption_bold
        }
        val padding = when (size) {
            "Large" -> Modifier.padding(horizontal = 24.dp, vertical = 10.dp)
            "Medium" -> Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
            else -> Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (icon != null) {
                Icon(
                    painter = icon,
                    contentDescription = null,
                    tint = textColor,
                    modifier = Modifier.size(18.dp)
                )
            }
            Text(
                text = text,
                color = textColor,
                style = textStyle,
                modifier = padding
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreMinariButton() {
    Column {
        MinariButton(
            text = "활",
            enabled = true,
            onClick = {},
            size = "Small",
            buttonColor = MinariBlue500,
            textColor = Color.White,
            line = false,
            icon = painterResource(id = R.drawable.ic_back)
        )

        MinariButton(
            text = "비활",
            enabled = false,
            onClick = {},
            size = "Small",
            buttonColor = MinariBlue500,
            textColor = Color.White,
            line = false
        )
    }
}
