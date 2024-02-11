package com.example.anitasks.screens.profile.model

import com.example.anitasks.core.data.model.Exam
import com.example.anitasks.core.data.model.Lesson
import com.example.anitasks.core.data.model.LessonType
import com.example.anitasks.core.data.model.UserData

data class ProfileUiState(
    val user:UserData?=null,
    val lessons:List<Lesson> = emptyList(),
    val exams:List<Exam> = emptyList(),
    val selectedLessonType:LessonType? = null
)
