package com.example.anitasks.screens.add_lesson.model

import com.example.anitasks.core.data.model.DayOfWeek
import com.example.anitasks.core.data.model.LessonType
import com.example.anitasks.core.data.model.Subject

data class AddLessonUiState(
    val id: Long? = null,
    val dayOfWeek: DayOfWeek? = null,
    val week: Int? = null,
    val lessonType: LessonType? = null,
    val startTime: String? = null,
    val location: String? = null,
    val subjectId: Long? = null,
    val subject: Subject? = null,
    val showProgressDialog:Boolean=false
)

