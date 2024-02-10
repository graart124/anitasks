package com.example.anitasks.screens.add_lesson.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.anitasks.core.data.model.LessonType
import com.example.anitasks.ui.theme.AppTextStyle
import com.example.anitasks.ui.theme.NavBarColor
import com.example.anitasks.ui.theme.getColor

@Composable
fun LessonTypes(
    selectedLessonType: LessonType?,
    onSelect: (LessonType) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        LessonTypeItem(
            modifier = Modifier.weight(1f),
            lessonType = LessonType.LECTURE,
            selectedLessonType = selectedLessonType,
            onClick = onSelect
        )
        Spacer(modifier = Modifier.width(6.dp))
        LessonTypeItem(
            modifier = Modifier.weight(1f),
            lessonType = LessonType.PRACTICAL,
            selectedLessonType = selectedLessonType,
            onClick = onSelect
        )
        Spacer(modifier = Modifier.width(6.dp))
        LessonTypeItem(
            modifier = Modifier.weight(1f),
            lessonType = LessonType.SEMINAR,
            selectedLessonType = selectedLessonType,
            onClick = onSelect
        )
    }
}

@Composable
fun LessonTypeItem(
    modifier: Modifier,
    lessonType: LessonType,
    selectedLessonType: LessonType?,
    onClick: (LessonType) -> Unit
) {
    val selected = selectedLessonType == lessonType
    Box(
        modifier = modifier
            .background(
                color = lessonType
                    .getColor()
                    .copy(if (selected) 1f else 0.7f), shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = if (selected) 2.dp else 0.dp,
                color = NavBarColor,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable {
                onClick(lessonType)
            }
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = lessonType.displayName,
            style = AppTextStyle.RobotoSemiBold.sp20.copy(color = Color.White)
        )
    }
}