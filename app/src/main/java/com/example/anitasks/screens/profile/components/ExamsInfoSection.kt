package com.example.anitasks.screens.profile.components

import androidx.compose.runtime.Composable
import com.example.anitasks.core.data.model.Exam
import java.time.LocalDate

@Composable
fun ExamsInfoSection(
    exams: List<Exam>
) {
    val scheduledExams = exams.filter { it.date.isAfter(LocalDate.now()) }
    val completedExams = exams.filter { it.date.isBefore(LocalDate.now()) }
    val averageMark = calculateAverageMark(exams)



}

fun calculateAverageMark(exams: List<Exam>): Float? {
    var totalMark = 0f
    var examCount = 0
    for (exam in exams) {
        exam.mark?.let { mark ->
            totalMark += mark
            examCount++
        }
    }

    return if (examCount > 0) {
        totalMark / examCount.toFloat()
    } else {
        null
    }
}