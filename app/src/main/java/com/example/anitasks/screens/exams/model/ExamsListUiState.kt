package com.example.anitasks.screens.exams.model

import com.example.anitasks.core.data.model.Exam

data class ExamsListUiState(
    val exams:List<Exam> = emptyList(),
    val loading:Boolean = true,
    val selectedExam:Exam? = null
)
