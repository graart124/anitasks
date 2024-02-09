package com.example.anitasks.screens.add_subject

import android.app.AlertDialog
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anitasks.core.data.model.Subject
import com.example.anitasks.core.features.subjects.repository.SubjectRepository
import com.example.anitasks.screens.add_subject.model.AddSubjectUiState
import com.example.anitasks.ui.util.Action
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddSubjectViewModel @Inject constructor(
    private val repository: SubjectRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(AddSubjectUiState())
    val uiState = _uiState.asStateFlow()

    private val _actionResult = MutableStateFlow(Action())
    val actionResult = _actionResult.asStateFlow()

    fun initSubject(subject: Subject?) {
        if (subject == null) return
        _uiState.update {
            it.copy(
                id = subject.id,
                subjectName = subject.name,
                teacherName = subject.teacherName
            )
        }
    }

    fun onSubjectNameChanged(subjectName: String) {
        _uiState.update { it.copy(subjectName = subjectName) }
    }

    fun onTeacherNameChanged(teacherName: String) {
        _uiState.update { it.copy(teacherName = teacherName) }
    }

    fun saveSubject() {
        val id = _uiState.value.id
        val name = _uiState.value.subjectName
        val teacherName = _uiState.value.teacherName

        if (name.isNullOrEmpty()) {
            _actionResult.update { Action(info = "Заповніть назву предмету", success = false) }
            return
        }

        viewModelScope.launch(
            CoroutineExceptionHandler { _, thr ->
                thr.printStackTrace()
            }
        ) {
            _uiState.update { it.copy(showProgressDialog = true) }
            try {
                if (id == null) {
                    repository.createSubject(
                        name = name,
                        teacherName = teacherName
                    )
                    _actionResult.update { Action(info = "Предмет успішно додано", success = true) }
                } else {
                    repository.updateSubject(
                        id = id,
                        name = name,
                        teacherName = teacherName
                    )
                    _actionResult.update {
                        Action(
                            info = "Інформацію про предмет успішно оновленоя",
                            success = true
                        )
                    }
                }

            } catch (_: Exception) {
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

    fun onDeleteClick(context: Context) {
        if (_uiState.value.id == null) {
            clearSubjectData()
        } else {
            showDeleteDialog(context)
        }
    }

    private fun clearSubjectData() {
        _uiState.update { AddSubjectUiState() }
    }

    private fun showDeleteDialog(context: Context) {
        AlertDialog.Builder(context)
            .setTitle("Видалити предмет?")
            .setMessage("Ви впевнені, що хочете видалити цей предмет?\nБудуть видалені також усі заняття з цього предмету")
            .setPositiveButton("Видалити") { _, _ ->
                deleteSubject()
            }
            .setNegativeButton("Скасувати", null)
            .show()
    }

    private fun deleteSubject() {
        viewModelScope.launch(
            CoroutineExceptionHandler { _, thr ->
                thr.printStackTrace()
            }
        ) {
            _uiState.update { it.copy(showProgressDialog = true) }
            try {
                repository.deleteSubjectById(uiState.value.id!!)
                _actionResult.update { Action(info = "Предмет успішно видалено", success = true) }
            } catch (e: Exception) {
                _actionResult.update {
                    Action(
                        info = "Упс, щось пішло не так\nБудь ласка,cпробуйте знову",
                        success = false
                    )
                }
            } finally {
                _uiState.update { it.copy(showProgressDialog = true) }
            }
        }
    }


}