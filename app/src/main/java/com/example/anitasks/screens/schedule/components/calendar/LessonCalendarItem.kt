package com.example.anitasks.screens.schedule.components.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.anitasks.core.data.model.Lesson
import com.example.anitasks.ui.theme.AppTextStyle
import com.example.anitasks.ui.theme.LightBlack
import com.example.anitasks.ui.theme.getColor

@Composable
fun LessonCalendarItem(
    modifier: Modifier,
    lesson: Lesson,
    onLessonClick: (Lesson) -> Unit,
    heightOfCell:Dp
) {
    Column(
        modifier = modifier
            .height(heightOfCell)
            .padding(horizontal = 1.dp)
            .background(
                color = lesson.lessonType.getColor(),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable {
                onLessonClick(lesson)
            }
            .padding(horizontal = 4.dp, vertical = 8.dp)
    ) {
        Text(
            text = lesson.subject?.name ?: "",
            textAlign = TextAlign.Center,
            style = AppTextStyle.RobotoBold.sp10.copy(color = LightBlack),
            maxLines = 2
        )
        Spacer(modifier = Modifier.height(2.dp))
        if (lesson.location != null) {
            Text(
                text = lesson.location!!,
                textAlign = TextAlign.Center,
                style = AppTextStyle.RobotoRegular.sp8.copy(color = LightBlack),
                maxLines = 1
            )
        }
    }
}