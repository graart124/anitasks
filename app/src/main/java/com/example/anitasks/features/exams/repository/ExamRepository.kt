package com.example.anitasks.features.exams.repository

import com.example.anitasks.core.data.local.room.dao.ExamDao
import com.example.anitasks.core.data.local.room.dao.SubjectDao
import com.example.anitasks.core.data.model.Exam
import java.time.LocalDate

class ExamRepository(
    private val dao: ExamDao,
    private val subjectDao: SubjectDao
) {
    suspend fun getAllExams(): List<Exam> {
        val exams = dao.getAllExams()
        val examsWithSubjectInfo = exams.map { exam ->
            val examSubject = subjectDao.getSubjectById(exam.subjectId)
            exam.copy(subject = examSubject)
        }
        return examsWithSubjectInfo
    }

    suspend fun createExam(
        startTime: String,
        date: LocalDate,
        location: String?,
        subjectId: Long,
        mark: Float? = null
    ) {
        dao.insertExam(
            Exam(
                startTime = startTime,
                date = date,
                location = location,
                subjectId = subjectId,
                mark = mark
            )
        )
    }

    suspend fun updateExam(
        id: Long,
        startTime: String,
        date: LocalDate,
        location: String?,
        subjectId: Long,
        mark: Float?
    ) {
        dao.updateExam(
            Exam(
                id = id,
                startTime = startTime,
                date = date,
                location = location,
                subjectId = subjectId,
                mark = mark
            )
        )
    }


    suspend fun updateExam(
        exam: Exam
    ) {
        dao.updateExam(exam)
    }

    suspend fun deleteExam(exam: Exam) {
        dao.deleteExam(exam)
    }

    suspend fun deleteExamById(examId: Long) {
        dao.deleteExamById(examId)
    }
}
