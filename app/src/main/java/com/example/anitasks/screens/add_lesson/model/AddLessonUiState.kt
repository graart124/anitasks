package com.example.anitasks.screens.add_lesson.model

import com.example.anitasks.core.data.model.DayOfWeek
import com.example.anitasks.core.data.model.LessonType
import com.example.anitasks.core.data.model.Subject

data class AddLessonUiState(
    var id: Long? = null,
    var dayOfWeek: DayOfWeek? = null,
    var week: Int? = null,
    var lessonType: LessonType? = null,
    var startTime: String? = null,
    var location: String? = null,
    var subjectId: Long? = null,
    var subject: Subject? = null
)

