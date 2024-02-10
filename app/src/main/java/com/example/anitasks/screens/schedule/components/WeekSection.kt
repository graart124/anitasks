package com.example.anitasks.screens.schedule.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.anitasks.R
import com.example.anitasks.ui.theme.AppTextStyle


@Composable
fun WeekSection(
    currentWeek: Int,
    onPrevWeekCLick: () -> Unit,
    onNextWeekCLick: () -> Unit,
    weeksOnSchedule: Int = 2
) {
    val imageSize = 24.dp
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (currentWeek != 1) {
            Image(
                modifier = Modifier
                    .size(imageSize)
                    .rotate(180f)
                    .clickable {
                        onPrevWeekCLick()
                    },
                colorFilter = ColorFilter.tint(color = Color.White),
                painter = painterResource(id = R.drawable.ic_arrow_right),
                contentDescription = "button left"
            )
        } else {
            Spacer(modifier = Modifier.size(imageSize))
        }
        Text(
            text = "Тиждень $currentWeek",
            style = AppTextStyle.RobotoSemiBold.sp16.copy(color = Color.White),
        )
        if (currentWeek != weeksOnSchedule) {
            Image(
                modifier = Modifier
                    .size(imageSize)
                    .clickable {
                        onNextWeekCLick()
                    },
                colorFilter = ColorFilter.tint(color = Color.White),
                painter = painterResource(id = R.drawable.ic_arrow_right),
                contentDescription = "button right"
            )
        } else {
            Spacer(modifier = Modifier.size(imageSize))
        }
    }
}