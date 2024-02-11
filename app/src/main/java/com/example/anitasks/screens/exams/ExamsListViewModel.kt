package com.example.anitasks.screens.exams

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anitasks.features.exams.repository.ExamRepository
import com.example.anitasks.screens.exams.model.ExamsListUiState
import com.example.anitasks.ui.util.Action
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExamsListViewModel @Inject constructor(
    private val repository: ExamRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ExamsListUiState())
    val uiState = _uiState.asStateFlow()

    private val _actionResult = MutableStateFlow(Action())
    val actionResult = _actionResult.asStateFlow()

    init {
        loadExams()
    }

    fun loadExams() {
        viewModelScope.launch(
            CoroutineExceptionHandler { _, thr ->
                thr.printStackTrace()
                _actionResult.update {
                    Action(
                        success = false,
                        info = "Не вдалося завантажити екзамени"
                    )
                }
            }
        ) {
            _uiState.update { it.copy(loading = true) }
            try {
                val exams = repository.getAllExams().sortedByDescending { it.date }
                _uiState.update { it.copy(exams = exams) }
                _actionResult.update { Action(success = true) }
            } catch (e: Exception) {
                _actionResult.update { it.copy(success = false, info = "Виникла помилка: $e") }
            } finally {
                _uiState.update { it.copy(loading = false) }

            }
        }

    }

}