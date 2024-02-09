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
import com.example.anitasks.ui.theme.AppTextStyle
import com.example.anitasks.ui.theme.NavBarColor
import com.example.anitasks.ui.theme.SelectedNavBarItemColor


@Composable
fun NumberOfWeek(
    selectedWeek: Int?,
    onSelect: (Int) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        WeekItem(
            modifier = Modifier.weight(1f),
            week = 1,
            selectedWeek = selectedWeek,
            onClick = onSelect
        )
        Spacer(modifier = Modifier.width(8.dp))
        WeekItem(
            modifier = Modifier.weight(1f),
            week = 2,
            selectedWeek = selectedWeek,
            onClick = onSelect
        )
    }
}

@Composable
fun WeekItem(
    modifier: Modifier,
    week: Int,
    selectedWeek: Int?,
    onClick: (Int) -> Unit
) {
    val selected = selectedWeek == week
    Box(
        modifier = modifier
            .background(color = SelectedNavBarItemColor.copy(if(selected) 1f else 0.7f), shape = RoundedCornerShape(8.dp))
            .border(
                width = if (selected) 2.dp else 0.dp,
                color = NavBarColor,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable {
                onClick(week)
            }
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Тиждень $week",
            style = AppTextStyle.RobotoSemiBold.sp20.copy(color = Color.White)
        )
    }
}