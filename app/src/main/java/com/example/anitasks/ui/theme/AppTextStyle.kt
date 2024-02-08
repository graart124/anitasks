package com.example.anitasks.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

sealed class AppTextStyle(fontFamily: FontFamily, weight: FontWeight) {
    private val style = TextStyle(fontFamily = fontFamily, fontWeight = weight)

    val sp12 get() = style.copy(fontSize = 12.sp)
    val sp14 get() = style.copy(fontSize = 14.sp)
    val sp16 get() = style.copy(fontSize = 16.sp)
    val sp18 get() = style.copy(fontSize = 18.sp)
    val sp20 get() = style.copy(fontSize = 20.sp)
    val sp22 get() = style.copy(fontSize = 22.sp)
    val sp32 get() = style.copy(fontSize = 32.sp)
    val sp36 get() = style.copy(fontSize = 36.sp)

    data object RobotoRegular : AppTextStyle(Roboto, FontWeight.Normal)
    data object RobotoMedium : AppTextStyle(Roboto, FontWeight.Medium)
    data object RobotoLight : AppTextStyle(Roboto, FontWeight.Light)
    data object RobotoBold : AppTextStyle(Roboto, FontWeight.Bold)
}
