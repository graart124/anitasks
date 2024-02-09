package com.example.anitasks.ui.theme

import androidx.compose.ui.graphics.Color
import com.example.anitasks.core.data.model.LessonType
import com.example.anitasks.core.data.model.LessonType.*

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)

val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)


val Primary = Color(0xFF9F1DBF)
val NavBarColor = Color(0xFF804CB2)
val SelectedNavBarItemColor = Color(0xFF80B3FF)
val UnselectedNavBarItemColor = Color(0xFFEBEBF5).copy(alpha = 0.7f)
val Background = Color(0xFF21283F)
val PurpleLight = Color(0xFFA85CE3)
val PurpleDark = Color(0xFF404471)

fun getColorByLessonType(lessonType: LessonType): Color {
    return when (lessonType) {
        LECTURE -> LectureColor
        SEMINAR -> SeminarColor
        PRACTICAL -> PracticalColor
    }
}

val LectureColor = Color(0xFFFFC374)
val PracticalColor = Color(0xFF4AD2C9)
val SeminarColor = Color(0xFF536EFF)

val Orange = Color(0xFFFA7942)
val DarkGray = Color(0xFF494442)
val MediumGray = Color(0xFF9E9E9E)
val LightGray = Color(0xFFF6F6F6)
val DriverColor = Color(0x14212121)


val Green = Color(0xFF3AB54A)

val Red = Color(0xFFEE4D4D)

val StatusBarColor = Color(0xFF999999)
val NavigationBarColor = Color(0xFFF2F2F2)