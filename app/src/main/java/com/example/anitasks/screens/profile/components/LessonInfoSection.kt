package com.example.anitasks.screens.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.anitasks.core.data.model.Lesson
import com.example.anitasks.core.data.model.LessonType
import com.example.anitasks.ui.theme.AppTextStyle
import com.example.anitasks.ui.theme.PurpleDark
import com.example.anitasks.ui.theme.getColor

@Composable
fun LessonInfoSection(
    lesson: List<Lesson> = emptyList(),
    selectedLessonType: LessonType?,
    onLessonTypeClick: (LessonType) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .background(color = PurpleDark, shape = RoundedCornerShape(8.dp))
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Інформація про заняття",
            style = AppTextStyle.RobotoSemiBold.sp28.copy(Color.White)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            LessonPieChart(lessons = lesson, selectedLessonType = selectedLessonType)
            Column(
                modifier = Modifier.padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                LessonTypeItem(lessonType = LessonType.LECTURE, onClick = onLessonTypeClick)
                Spacer(modifier = Modifier.height(12.dp))
                LessonTypeItem(lessonType = LessonType.PRACTICAL, onClick = onLessonTypeClick)
                Spacer(modifier = Modifier.height(12.dp))
                LessonTypeItem(lessonType = LessonType.SEMINAR, onClick = onLessonTypeClick)
            }
        }
    }
}

@Composable
fun LessonTypeItem(
    lessonType: LessonType,
    onClick: (LessonType) -> Unit
) {
    Row(
        modifier = Modifier.clickable {
            onClick(lessonType)
        },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .size(16.dp)
                .background(color = lessonType.getColor(), shape = CircleShape)
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = lessonType.displayName,
            style = AppTextStyle.RobotoSemiBold.sp18.copy(Color.White)
        )
    }
}