package com.example.anitasks.screens.schedule.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.anitasks.core.data.model.DayOfWeek
import com.example.anitasks.core.data.model.Lesson
import com.example.anitasks.ui.theme.AppTextStyle
import com.example.anitasks.ui.theme.LightBlack
import com.example.anitasks.ui.theme.UnselectedNavBarItemColor
import com.example.anitasks.ui.theme.getColor

private val WIDTH_OF_HOURS = 30.dp
private val HEIGHT_OF_CELL = 75.dp

@Composable
fun CalendarView(
    week: Int,
    lessons: List<Lesson>
) {
    Column {
        Row {
            Box(modifier = Modifier.width(WIDTH_OF_HOURS))
            for (dayOfWeek in DayOfWeek.values()) {
                Text(
                    text = dayOfWeek.displayName.take(3),
                    modifier = Modifier.weight(1f),
                    style = AppTextStyle.RobotoRegular.sp14.copy(Color.White)
                )
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            //        verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            items((6..24).toList()) { hour ->
                Column {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            modifier = Modifier
                                .width(WIDTH_OF_HOURS)
                                .padding(vertical = 4.dp),
                            text = if (hour < 10) "0$hour" else hour.toString(),
                            style = AppTextStyle.RobotoSemiBold.sp12.copy(Color.White)
                        )
                        for (dayOfWeek in DayOfWeek.values()) {
                            val lesson = lessons.find {
                                it.dayOfWeek == dayOfWeek && it.startTime.startsWith(if (hour < 10) "0$hour:" else "$hour:") && it.week == week
                            }
                            if (lesson == null) {
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(HEIGHT_OF_CELL)
                                        .background(
                                            color = Color.Transparent
                                        )
                                )
                            } else {
                                LessonCalendarItem(modifier = Modifier.weight(1f), lesson = lesson)
                            }
                        }
                    }
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(0.5.dp)
                            .background(color = UnselectedNavBarItemColor)
                    )
                }
            }
        }
    }
}

@Composable
fun LessonCalendarItem(
    modifier: Modifier,
    lesson: Lesson
) {
    Column(
        modifier = modifier
            .height(HEIGHT_OF_CELL)
            .background(
                color = lesson.lessonType.getColor(),
                shape = RoundedCornerShape(8.dp)
            )
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