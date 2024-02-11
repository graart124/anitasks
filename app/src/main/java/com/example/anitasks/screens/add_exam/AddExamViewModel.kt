package com.example.anitasks.screens.add_exam

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anitasks.core.data.model.Exam
import com.example.anitasks.core.data.model.Subject
import com.example.anitasks.features.exams.repository.ExamRepository
import com.example.anitasks.screens.add_exam.model.AddExamUiState
import com.example.anitasks.ui.util.Action
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class AddExamViewModel @Inject constructor(
    private val repository: ExamRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(AddExamUiState())
    val uiState = _uiState.asStateFlow()

    private val _actionResult = MutableStateFlow(Action())
    val actionResult = _actionResult.asStateFlow()

    fun initExam(exam: Exam?) {
        if (exam == null) return
        _uiState.update {
            it.copy(
                id = exam.id,
                subjectId = exam.subjectId,
                subject = exam.subject,
                date = exam.date,
                location = exam.location,
                startTime = exam.startTime,
                mark = exam.mark
            )
        }
    }

    fun saveExam() {
        if (!validateExamData()) {
            return
        }

        val id = _uiState.value.id
        val subjectId = _uiState.value.subjectId
        val location = _uiState.value.location
        val startTime = _uiState.value.startTime
        val date = _uiState.value.date
        val mark = _uiState.value.mark

        viewModelScope.launch(
            CoroutineExceptionHandler { _, thr ->
                thr.printStackTrace()
                _actionResult.update {
                    it.copy(
                        info = "Упс, щось пішло не так\nБудь ласка,cпробуйте знову"
                    )
                }
            }
        ) {
            _uiState.update { it.copy(showProgressDialog = true) }
            try {
                if (id == null) {
                    repository.createExam(
                        date = date!!,
                        startTime = startTime!!,
                        location = location,
                        subjectId = subjectId!!
                    )
                    _actionResult.update {
                        Action(
                            info = "Екзамен успішно додано",
                            success = true
                        )
                    }
                } else {
                    repository.updateExam(
                        id = id,
                        startTime = startTime!!,
                        location = location,
                        subjectId = subjectId!!,
                        date = date!!,
                        mark = mark
                    )
                    _actionResult.update {
                        Action(
                            info = "Інформацію про екзамен успішно оновлено",
                            success = true
                        )
                    }
                }
            } catch (e: Exception) {
                _actionResult.update {
                    it.copy(
                        info = "Упс, щось пішло не так\nБудь ласка,cпробуйте знову\nПомилка: $e",
                        success = false
                    )
                }
            } finally {
                _uiState.update { it.copy(showProgressDialog = false) }
            }
        }
    }

    private fun validateExamData(): Boolean {
        if (_uiState.value.subjectId == null) {
            _actionResult.update { Action(info = "Оберіть предмет") }
            return false
        }
        if (_uiState.value.date == null) {
            _actionResult.update { Action(info = "Вкажіть дату екзамену") }
            return false
        }
        if (_uiState.value.startTime == null) {
            _actionResult.update { Action(info = "Оберіть час початку екзамену") }
            return false
        }
        return true
    }

    fun selectSubject(subject: Subject?) {
        _uiState.update {
            it.copy(
                subject = subject,
                subjectId = subject?.id
            )
        }
    }

    fun onLocationChanged(location: String) {
        _uiState.update { it.copy(location = location) }
    }

    fun selectStartTime(time: LocalTime) {
        _uiState.update { it.copy(startTime = time.format(DateTimeFormatter.ofPattern("HH:mm"))) }
    }

    fun selectDate(localDate: LocalDate) {
        _uiState.update { it.copy(date = localDate) }
    }

    fun onDeleteClick() {
        if (_uiState.value.id == null) {
            clearSubjectData()
        } else {
            showDeleteDialog()
        }
    }

    private fun clearSubjectData() {
        _uiState.update { AddExamUiState() }
    }

    private fun showDeleteDialog() {
        _uiState.update { it.copy(showDeleteDialog = true) }
    }

    fun dismissDeleteDialog() {
        _uiState.update { it.copy(showDeleteDialog = false) }
    }

    fun deleteExam() {
        viewModelScope.launch(
            CoroutineExceptionHandler { _, thr ->
                thr.printStackTrace()
            }
        ) {
            _uiState.update { it.copy(showProgressDialog = true) }
            try {
                repository.deleteExamById(uiState.value.id!!)
                _actionResult.update { Action(info = "Екзамен успішно видалено", success = true) }
            } catch (e: Exception) {
                _actionResult.update {
                    Action(
                        info = "Упс, щось пішло не так\nБудь ласка,cпробуйте знову",
                        success = false
                    )
                }
            } finally {
                _uiState.update { it.copy(showProgressDialog = false) }
            }
        }
    }

}