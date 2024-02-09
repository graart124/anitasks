package com.example.anitasks.screens.subjects

import android.app.AlertDialog
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anitasks.core.data.model.Subject
import com.example.anitasks.core.features.subjects.repository.SubjectRepository
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
class SubjectListViewModel @Inject constructor(
    private val repository: SubjectRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SubjectListUiState())
    val uiState = _uiState.asStateFlow()

    private val _actionResult = MutableStateFlow(Action())
    val actionResult = _actionResult.asStateFlow()

    init {
        loadSubjects()
    }

    fun loadSubjects() {
        viewModelScope.launch(
            CoroutineExceptionHandler { _, thr ->
                thr.printStackTrace()
                _actionResult.update {
                    it.copy(
                        success = false,
                        info = "Не вдалося завантажити предмети("
                    )
                }
            }
        ) {
            _uiState.update { it.copy(loading = true) }
            try {
                val subjects = repository.getAllSubjects()
                _uiState.update { it.copy(subjects = subjects) }
            } catch (e: Exception) {
                _actionResult.update { it.copy(success = false, info = "Виникла помилка: $e") }
            } finally {
                _uiState.update { it.copy(loading = false) }

            }
        }

    }

    fun onDeleteSubject(subject: Subject, context: Context) {
        AlertDialog.Builder(context)
            .setTitle("Видалити предмет?")
            .setMessage("Ви впевнені, що хочете видалити цей предмет?\nБудуть видалені також усі заняття з цього предмету")
            .setPositiveButton("Видалити") { _, _ ->
                deleteSubject(subject)
            }
            .setNegativeButton("Скасувати", null)
            .show()
    }

    private fun deleteSubject(subject: Subject) {
        viewModelScope.launch(
            CoroutineExceptionHandler { _, thr ->
                thr.printStackTrace()
            }
        ) {
            _uiState.update { it.copy(loading = true) }
            try {
                repository.deleteSubject(subject = subject)
                _actionResult.update { Action(info = "Предмет успішно видалено", success = true) }

            } catch (e: Exception) {
                _actionResult.update {
                    Action(
                        info = "Упс, щось пішло не так\nБудь ласка,cпробуйте знову",
                        success = false
                    )
                }
            } finally {
                loadSubjects()
            }
        }
    }
}