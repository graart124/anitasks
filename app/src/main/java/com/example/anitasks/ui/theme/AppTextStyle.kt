package com.example.anitasks.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

sealed class AppTextStyle(fontFamily: FontFamily, weight: FontWeight) {
    private val style = TextStyle(fontFamily = fontFamily, fontWeight = weight)

    val sp14 get() = style.copy(fontSize = 14.sp)
    val sp16 get() = style.copy(fontSize = 16.sp)
    val sp18 get() = style.copy(fontSize = 18.sp)
    val sp20 get() = style.copy(fontSize = 20.sp)
    val sp22 get() = style.copy(fontSize = 22.sp)
    val sp36 get() = style.copy(fontSize = 36.sp)

    object RobotoRegular : AppTextStyle(Roboto, FontWeight.Normal)
    object RobotoMedium : AppTextStyle(Roboto, FontWeight.Medium)
    object RobotoLight : AppTextStyle(Roboto, FontWeight.Light)
    object RobotoBold : AppTextStyle(Roboto, FontWeight.Bold)
}
