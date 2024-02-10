package com.example.anitasks.screens.schedule.model

import com.example.anitasks.core.data.model.Lesson

data class ScheduleScreenUiState(
    val currentWeek:Int = 1,
    val lessons:List<Lesson> = emptyList(),
    val loading:Boolean = true
)
