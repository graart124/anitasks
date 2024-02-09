package com.example.anitasks.screens.add_lesson

import android.app.AlertDialog
import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.anitasks.R
import com.example.anitasks.core.data.model.DayOfWeek
import com.example.anitasks.core.data.model.LessonType
import com.example.anitasks.core.data.model.Subject
import com.example.anitasks.screens.add_lesson.model.AddLessonUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject


@HiltViewModel
class AddLessonViewModel @Inject constructor(

) : ViewModel() {

    private val _uiState = MutableStateFlow(AddLessonUiState())
    val uiState = _uiState.asStateFlow()


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

}