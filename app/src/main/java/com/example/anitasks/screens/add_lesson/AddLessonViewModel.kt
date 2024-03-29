package com.example.anitasks.screens.add_lesson

import android.app.AlertDialog
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anitasks.R
import com.example.anitasks.core.data.model.DayOfWeek
import com.example.anitasks.core.data.model.Lesson
import com.example.anitasks.core.data.model.LessonType
import com.example.anitasks.core.data.model.Subject
import com.example.anitasks.features.lessons.repository.LessonRepository
import com.example.anitasks.screens.add_lesson.model.AddLessonArgs
import com.example.anitasks.screens.add_lesson.model.AddLessonUiState
import com.example.anitasks.ui.util.ActionWithData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject


@HiltViewModel
class AddLessonViewModel @Inject constructor(
    private val repository: LessonRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddLessonUiState())
    val uiState = _uiState.asStateFlow()

    private val _actionResult = MutableStateFlow(ActionWithData<String>())
    val actionResult = _actionResult.asStateFlow()

    fun initLesson(lesson: Lesson?) {
        if (lesson == null) return
        _uiState.update {
            it.copy(
                id = lesson.id,
                subjectId = lesson.subjectId,
                subject = lesson.subject,
                lessonType = lesson.lessonType,
                location = lesson.location,
                dayOfWeek = lesson.dayOfWeek,
                startTime = lesson.startTime,
                week = lesson.week
            )
        }
    }


    fun initLessonWithArgs(addLessonArgs: AddLessonArgs?) {
        if (addLessonArgs == null) return
        _uiState.update {
            it.copy(
                dayOfWeek = addLessonArgs.dayOfWeek,
                startTime = addLessonArgs.startTime,
                week = addLessonArgs.week
            )
        }
    }

    fun selectLessonType(lessonType: LessonType) {
        _uiState.update { it.copy(lessonType = lessonType) }
    }

    fun selectWeek(week: Int) {
        _uiState.update { it.copy(week = week) }
    }

    fun showDayOfWeekDialog(context: Context) {
        val items = DayOfWeek.values().map { it.displayName }.toTypedArray()
        val builder = AlertDialog.Builder(context)
        builder.setTitle(context.getString(R.string.pick_day_of_week))
            .setItems(items) { _, which ->
                _uiState.update { it.copy(dayOfWeek = DayOfWeek.values()[which]) }
            }
        builder.create().show()
    }

    fun selectSubject(subject: Subject?) {
        _uiState.update {
            it.copy(
                subject = subject,
                subjectId = subject?.id
            )
        }
    }

    fun selectStartTime(time: LocalTime) {
        _uiState.update { it.copy(startTime = time.format(DateTimeFormatter.ofPattern("HH:mm"))) }
    }

    fun onLocationChanged(location: String) {
        _uiState.update { it.copy(location = location) }
    }

    fun saveLesson() {
        if (!validateLessonData()) {
            return
        }

        val id = _uiState.value.id
        val subjectId = _uiState.value.subjectId
        val location = _uiState.value.location
        val lessonType = _uiState.value.lessonType
        val dayOfWeek = _uiState.value.dayOfWeek
        val startTime = _uiState.value.startTime
        val week = _uiState.value.week

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
                    repository.createLesson(
                        dayOfWeek = dayOfWeek!!,
                        week = week!!,
                        lessonType = lessonType!!,
                        startTime = startTime!!,
                        location = location,
                        subjectId = subjectId!!
                    )
                    _actionResult.update {
                        ActionWithData(
                            info = "Заняття успішно додано",
                            success = true
                        )
                    }
                } else {
                    repository.updateLesson(
                        id = id,
                        dayOfWeek = dayOfWeek!!,
                        week = week!!,
                        lessonType = lessonType!!,
                        startTime = startTime!!,
                        location = location,
                        subjectId = subjectId!!
                    )
                    _actionResult.update {
                        ActionWithData(
                            info = "Інформацію про заняття успішно оновлено",
                            success = true,
                            data = "edit"
                        )
                    }
                }
                clearSubjectData()
            } catch (e: Exception) {
                _actionResult.update {
                    it.copy(
                        info = e.message
                    )
                }
            } finally {
                _uiState.update { it.copy(showProgressDialog = false) }
            }
        }
    }

    private fun validateLessonData(): Boolean {
        if (_uiState.value.subjectId == null) {
            _actionResult.update { ActionWithData(info = "Оберіть предмет") }
            return false
        }
        if (_uiState.value.lessonType == null) {
            _actionResult.update { ActionWithData(info = "Оберіть тип заняття") }
            return false
        }
        if (_uiState.value.dayOfWeek == null) {
            _actionResult.update { ActionWithData(info = "Оберіть день тижня") }
            return false
        }
        if (_uiState.value.startTime == null) {
            _actionResult.update { ActionWithData(info = "Оберіть час початку заняття") }
            return false
        }
        if (_uiState.value.week == null) {
            _actionResult.update { ActionWithData(info = "Вкажіть тиждень") }
            return false
        }
        return true
    }

    fun onDeleteClick(context: Context) {
        if (_uiState.value.id == null) {
            clearSubjectData()
        } else {
            showDeleteDialog(context)
        }
    }

    private fun clearSubjectData() {
        _uiState.update { AddLessonUiState() }
    }

    private fun showDeleteDialog(context: Context) {
        AlertDialog.Builder(context)
            .setTitle("Видалити заняття?")
            .setMessage("Ви впевнені, що хочете видалити це заняття?")
            .setPositiveButton("Видалити") { _, _ ->
                deleteLesson()
            }
            .setNegativeButton("Скасувати", null)
            .show()
    }

    private fun deleteLesson() {
        viewModelScope.launch(
            CoroutineExceptionHandler { _, thr ->
                thr.printStackTrace()
            }
        ) {
            _uiState.update { it.copy(showProgressDialog = true) }
            try {
                repository.deleteLessonById(uiState.value.id!!)
                _actionResult.update {
                    ActionWithData(
                        info = "Заняття успішно видалено",
                        success = true
                    )
                }
                clearSubjectData()
            } catch (e: Exception) {
                _actionResult.update {
                    ActionWithData(
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