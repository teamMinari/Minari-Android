package com.nohjason.minari.screens.ui.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nohjason.minari.R
import com.nohjason.minari.ui.theme.MinariGray100
import com.nohjason.minari.ui.theme.MinariGray700
import com.nohjason.minari.ui.theme.MinariWhite
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import com.nohjason.minari.ui.theme.MinariBlue500
import com.nohjason.minari.ui.theme.MinariGray800
import com.nohjason.minari.ui.theme.button_medium
import com.nohjason.minari.ui.theme.caption_bold
import com.nohjason.minari.ui.theme.caption_medium

@Composable
fun NewsButton(
    icon: Painter?,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(72.dp)
    ) {
        Button(
            onClick = onClick,
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, if (isSelected) MinariBlue500 else MinariGray100),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isSelected) MinariBlue500 else MinariWhite,
                contentColor = if (isSelected) Color.White else MinariGray700
            ),
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier.size(64.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = icon ?: painterResource(id = R.drawable.ic_visibility),
                    contentDescription = "카테고리 아이콘",
                    modifier = Modifier.size(32.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = text,
            style = button_medium,
            color = MinariGray700,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}





//@Preview(showBackground = true)
//@Composable
//fun PreviewNewsButton() {
//    NewsButton()
//}
