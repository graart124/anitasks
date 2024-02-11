package com.example.anitasks.screens.add_exam.model

import com.example.anitasks.core.data.model.Subject
import java.time.LocalDate

data class AddExamUiState(
    val id: Long? = null,
    val date: LocalDate? = null,
    val startTime: String? = null,
    val location: String? = null,
    val subjectId: Long? = null,
    val subject: Subject? = null,
    val mark:Float? = null,
    val showProgressDialog: Boolean = false,
    val  showDeleteDialog:Boolean = false
)
