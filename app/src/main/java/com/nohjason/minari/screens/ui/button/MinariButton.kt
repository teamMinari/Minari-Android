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
import androidx.compose.foundation.layout.*
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun MinariButton(
    text: String,
    size: String,
    textColor: Color,
    buttonColor: Color,
    line: Boolean = false,
    enabled: Boolean = true,
    icon: Painter? = null,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    fun heightRatio(ratio: Float) = screenHeight * ratio
    fun widthRatio(ratio: Float) = screenWidth * ratio

    val border = if (line) BorderStroke(1.dp, MinariGray200) else null

    val (textStyle, padding) = when (size) {
        "Large" -> b2_bold to PaddingValues(
            horizontal = widthRatio(0.07f),
            vertical = heightRatio(0.014f)
        )
        "Medium" -> button_bold to PaddingValues(
            horizontal = widthRatio(0.055f),
            vertical = heightRatio(0.011f)
        )
        else -> caption_bold to PaddingValues(
            horizontal = widthRatio(0.045f),
            vertical = heightRatio(0.008f)
        )
    }

    Button(
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(widthRatio(0.033f)),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = buttonColor,
            disabledBackgroundColor = buttonColor.copy(alpha = 0.6f),
            contentColor = textColor,
            disabledContentColor = textColor.copy(alpha = 0.6f)
        ),
        border = border,
        elevation = ButtonDefaults.elevation(0.dp),
        modifier = modifier
            .imePadding()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (icon != null) {
                Icon(
                    painter = icon,
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(widthRatio(0.05f))
                )
            }
            Text(
                text = text,
                color = textColor,
                style = textStyle,
                modifier = Modifier.padding(padding)
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
fun MinariButtonPreview() {
    MinariButton(
        text = "로그인",
        size = "Large",
        textColor = Color.White,
        buttonColor = Color(0xFF3D5AFE),
        line = false,
        enabled = true,
        icon = null
    )
}

