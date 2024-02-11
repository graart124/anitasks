package com.example.anitasks.features.lessons.repository

import com.example.anitasks.core.data.local.room.dao.LessonDao
import com.example.anitasks.core.data.local.room.dao.SubjectDao
import com.example.anitasks.core.data.model.DayOfWeek
import com.example.anitasks.core.data.model.Lesson
import com.example.anitasks.core.data.model.LessonType

class LessonRepository(
    private val dao: LessonDao,
    private val subjectDao: SubjectDao
) {
    suspend fun getAllLessons(): List<Lesson> {
        return dao.getAllLessons()
    }

    suspend fun getLessonsByWeek(week: Int = 1): List<Lesson> {
        val lessons = dao.getLessonsByWeek(week)
        val lessonsWithSubjectInfo = lessons.map { lesson ->
            val lessonSubject = subjectDao.getSubjectById(lesson.subjectId)
            lesson.copy(subject = lessonSubject)
        }
        return lessonsWithSubjectInfo
    }

    suspend fun createLesson(
        dayOfWeek: DayOfWeek,
        week: Int,
        lessonType: LessonType,
        startTime: String,
        location: String?,
        subjectId: Long
    ) {
        dao.insertLesson(
            Lesson(
                dayOfWeek = dayOfWeek,
                week = week,
                lessonType = lessonType,
                startTime = startTime,
                location = location,
                subjectId = subjectId
            )
        )
    }

    suspend fun updateLesson(
        id: Long,
        dayOfWeek: DayOfWeek,
        week: Int,
        lessonType: LessonType,
        startTime: String,
        location: String?,
        subjectId: Long
    ) {
        dao.updateLesson(
            Lesson(
                id = id,
                dayOfWeek = dayOfWeek,
                week = week,
                lessonType = lessonType,
                startTime = startTime,
                location = location,
                subjectId = subjectId
            )
        )
    }

    suspend fun deleteLessonById(id: Long) {
        dao.deleteLessonById(id)
    }
}