package com.example.anitasks.screens.add_subject.model

data class AddSubjectUiState(
    var id: Long? = null,
    var subjectName: String? = null,
    var teacherName: String? = null,
    val showProgressDialog:Boolean = false,
    val showDeleteDialog:Boolean = false
)
