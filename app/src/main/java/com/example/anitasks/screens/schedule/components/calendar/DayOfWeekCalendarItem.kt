package com.example.anitasks.screens.schedule.components.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.anitasks.core.data.model.DayOfWeek
import com.example.anitasks.ui.theme.AppTextStyle

@Composable
fun DayOfWeekCalendarItem(
    modifier: Modifier,
    dayOfWeek: DayOfWeek
) {
    Column(
        modifier = modifier
            .wrapContentHeight()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = dayOfWeek.shortName,
            modifier = modifier,
            style = AppTextStyle.RobotoRegular.sp14.copy(Color.White)
        )
    }
}