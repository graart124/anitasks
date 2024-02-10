package com.example.anitasks.screens.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anitasks.core.features.lessons.repository.LessonRepository
import com.example.anitasks.screens.schedule.model.ScheduleScreenUiState
import com.example.anitasks.screens.subjects.model.SubjectListUiState
import com.example.anitasks.ui.util.Action
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val repository: LessonRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ScheduleScreenUiState())
    val uiState = _uiState.asStateFlow()

    private val _actionResult = MutableStateFlow(Action())
    val actionResult = _actionResult.asStateFlow()

    init {
        loadLessons()
    }

    fun loadLessons() {
        viewModelScope.launch(
            CoroutineExceptionHandler { _, thr ->
                thr.printStackTrace()
                _actionResult.update {
                    it.copy(
                        success = false,
                        info = "Не вдалося завантажити ваш розклад"
                    )
                }
            }
        ) {
            _uiState.update { it.copy(loading = true) }
            try {
                val lessons = repository.getLessonsByWeek(week = _uiState.value.currentWeek)
                _uiState.update { it.copy(lessons = lessons) }
            } catch (e: Exception) {
                _actionResult.update { it.copy(success = false, info = "Виникла помилка: $e") }
            } finally {
                _uiState.update { it.copy(loading = false) }
            }
        }
    }


}