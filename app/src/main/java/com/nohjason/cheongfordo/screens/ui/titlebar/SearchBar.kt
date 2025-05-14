package com.nohjason.cheongfordo.screens.ui.titlebar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nohjason.cheongfordo.R
import com.nohjason.cheongfordo.navigation.Screens
import com.nohjason.cheongfordo.screens.ui.text.MinariInputField
import com.nohjason.cheongfordo.ui.theme.MinariBlue
import com.nohjason.cheongfordo.ui.theme.pretendard_bold

@Composable
fun SearchBar(
    placeholderText: String = "검색",
    modifier: Modifier = Modifier,
    navController: NavController,
    onNavigate: () -> Unit = { navController.navigate(Screens.Search.rout) }
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF6F6F6))
            .clickable { onNavigate() }
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 포도 로고 (왼쪽)
        Image(
            painter = painterResource(id = R.drawable.grape), // 포도 로고 리소스 사용
            contentDescription = "logo",
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        // Placeholder 텍스트
        Text(
            text = placeholderText,
            fontFamily = pretendard_bold,
            fontSize = 15.sp,
            color = Color(0xFF8C8C8C),
            modifier = Modifier.weight(1f)
        )
        // 검색 아이콘 (오른쪽)
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "검색",
            modifier = Modifier
                .size(24.dp),
            tint = MinariBlue
        )
    }
}
