package com.example.anitasks.screens.schedule.components

import android.widget.CalendarView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.anitasks.core.data.model.DayOfWeek
import com.example.anitasks.core.data.model.Lesson
import com.example.anitasks.ui.theme.AppTextStyle
import com.example.anitasks.ui.theme.getColor

private val WIDTH_OF_HOURS = 26.dp
private val HEIGHT_OF_CELL = 75.dp

@Composable
fun CalendarView(
    week: Int,
    lessons: List<Lesson>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        item {
            //daysOfWeek
            Row {
                Box(modifier = Modifier.width(WIDTH_OF_HOURS))
                for (dayOfWeek in DayOfWeek.values()) {
                    Text(text = dayOfWeek.displayName, modifier = Modifier.weight(1f),
                        style = AppTextStyle.RobotoRegular.sp14.copy(color = Color.White))
                }
            }
        }

//        item {
//            //hours
//            Column {
//                for (hour in 6..24) {
//                    Text(
//                        modifier = Modifier
//                            .width(12.dp)
//                            .padding(vertical = 4.dp),
//                        text = "$hour:00"
//                    )
//                }
//            }
//        }


        items((6..24).toList()) { hour ->
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier
                        .width(12.dp)
                        .padding(vertical = 4.dp),
                    text = "$hour",
                    style = AppTextStyle.RobotoRegular.sp14.copy(color = Color.White)
                )
                for (dayOfWeek in DayOfWeek.values()) {
                    val lesson = lessons.find {
                        it.dayOfWeek == dayOfWeek && it.startTime.startsWith("$hour:") && it.week == week
                    }
                    if (lesson == null) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height((HEIGHT_OF_CELL * 1.5f))
                                .background(
                                    color = Color.White
                                )
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height((HEIGHT_OF_CELL * 1.5f))
                                .background(
                                    color = lesson.lessonType.getColor()
                                )
                        ) {
                            Text(
                                text = "${lesson.startTime} - ${lesson.subject?.name ?: ""}",
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp)
                            )
                        }
                    }
                }

            }
        }
    }

}