package com.example.anitasks.screens.subjects.model

import com.example.anitasks.core.data.model.Subject

data class SubjectListUiState(
    val subjects: List<Subject> = emptyList(),
    val loading: Boolean = true
)
