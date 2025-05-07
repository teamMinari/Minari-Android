package com.nohjason.minari.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nohjason.minari.R

// Set of Material typography styles to start with
//val Typography = Typography(
//    bodyLarge = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Normal,
//        fontSize = 16.sp,
//        lineHeight = 24.sp,
//        letterSpacing = 0.5.sp
//    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */

//)


// FontFamily 정의
val rixfont = FontFamily(Font(R.font.test))
val pretendard_medium = FontFamily(Font(R.font.pretendard_medium))
val pretendard_regular = FontFamily(Font(R.font.pretendard_regular))
val pretendard_bold = FontFamily(Font(R.font.pretendard_bold))
val pretendard_extra_bold = FontFamily(Font(R.font.pretendard_extra_bold))
val pretendard_semibold = FontFamily(Font(R.font.pretendard_semibold))
val inter_bold = FontFamily(Font(R.font.inter_bold))
val inter_semibold = FontFamily(Font(R.font.inter_semibold))

// TextStyle 정의
// FontFamily 정의는 그대로 유지

val h1_bold = TextStyle(
    fontFamily = pretendard_bold,
    fontWeight = FontWeight.Bold,
    fontSize = 32.sp,
    lineHeight = 41.6.sp
)

val h1_medium = TextStyle(
    fontFamily = pretendard_medium,
    fontWeight = FontWeight.Medium,
    fontSize = 32.sp,
    lineHeight = 41.6.sp
)

val h2_bold = TextStyle(
    fontFamily = inter_bold,
    fontWeight = FontWeight.Bold,
    fontSize = 28.sp,
    lineHeight = 36.4.sp
)

val h2_medium = TextStyle(
    fontFamily = pretendard_medium,
    fontWeight = FontWeight.Medium,
    fontSize = 28.sp,
    lineHeight = 36.4.sp
)

val h3_bold = TextStyle(
    fontFamily = inter_bold,
    fontWeight = FontWeight.SemiBold,
    fontSize = 24.sp,
    lineHeight = 33.6.sp
)

val h3_medium = TextStyle(
    fontFamily = pretendard_medium,
    fontWeight = FontWeight.Medium,
    fontSize = 24.sp,
    lineHeight = 33.6.sp
)

val h4_bold = TextStyle(
    fontFamily = inter_bold,
    fontWeight = FontWeight.Bold,
    fontSize = 20.sp,
    lineHeight = 28.sp
)

val h4_medium = TextStyle(
    fontFamily = pretendard_medium,
    fontWeight = FontWeight.Medium,
    fontSize = 20.sp,
    lineHeight = 28.sp
)

val b1_bold = TextStyle(
    fontFamily = inter_bold,
    fontWeight = FontWeight.SemiBold,
    fontSize = 18.sp,
    lineHeight = 27.sp
)

val b1_medium = TextStyle(
    fontFamily = pretendard_medium,
    fontWeight = FontWeight.Medium,
    fontSize = 18.sp,
    lineHeight = 27.sp
)

val b2_bold = TextStyle(
    fontFamily = inter_bold,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    lineHeight = 24.sp
)

val b2_medium = TextStyle(
    fontFamily = pretendard_medium,
    fontWeight = FontWeight.Medium,
    fontSize = 16.sp,
    lineHeight = 24.sp
)

val button_bold = TextStyle(
    fontFamily = inter_bold,
    fontWeight = FontWeight.SemiBold,
    fontSize = 14.sp,
    lineHeight = 21.sp
)

val button_medium = TextStyle(
    fontFamily = pretendard_medium,
    fontWeight = FontWeight.Medium,
    fontSize = 14.sp,
    lineHeight = 21.sp
)

val caption_bold = TextStyle(
    fontFamily = inter_bold,
    fontWeight = FontWeight.SemiBold,
    fontSize = 12.sp,
    lineHeight = 18.sp
)

val caption_medium = TextStyle(
    fontFamily = pretendard_regular,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    lineHeight = 18.sp
)

