package com.example.anitasks.screens.add_lesson.model

import android.os.Parcelable
import com.example.anitasks.core.data.model.DayOfWeek
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddLessonArgs(
    val dayOfWeek: DayOfWeek,
    val startTime:String,
    val week: Int
):Parcelable
