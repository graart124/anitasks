package com.example.anitasks.screens.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anitasks.core.data.model.LessonType
import com.example.anitasks.features.exams.repository.ExamRepository
import com.example.anitasks.features.lessons.repository.LessonRepository
import com.example.anitasks.features.user.repository.UserRepository
import com.example.anitasks.screens.profile.model.ProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val lessonRepository: LessonRepository,
    private val examRepository: ExamRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState = _uiState.asStateFlow()


    init {
        _uiState.update {
            it.copy(
                user = userRepository.getUserData()
            )
        }
        loadLessons()
        loadExams()
    }
    fun onResume(){
        loadExams()
        loadLessons()
    }

    private fun loadExams() {
        viewModelScope.launch(
            CoroutineExceptionHandler { _, thr ->
                thr.printStackTrace()
            }
        ) {
            try {
                val exams = examRepository.getAllExams()
                _uiState.update { it.copy(exams = exams) }
            } catch (e: Exception) {
                Log.d("MyLog",e.message?:"")
            }
        }
    }


   private fun loadLessons() {
        viewModelScope.launch(
            CoroutineExceptionHandler { _, thr ->
                thr.printStackTrace()
            }
        ) {
            try {
                val lessons = lessonRepository.getAllLessons()
                _uiState.update { it.copy(lessons = lessons) }
            } catch (e: Exception) {
                Log.d("MyLog",e.message?:"")
            }
        }
    }

    fun onLessonTypeClick(lessonType: LessonType) {
        _uiState.update { it.copy(selectedLessonType = lessonType) }
    }

}