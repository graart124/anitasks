package com.example.anitasks.features.lessons.repository

import com.example.anitasks.core.data.local.room.dao.LessonDao
import com.example.anitasks.core.data.local.room.dao.SubjectDao
import com.example.anitasks.core.data.model.DayOfWeek
import com.example.anitasks.core.data.model.Lesson
import com.example.anitasks.core.data.model.LessonType
import java.time.Duration.ofMinutes
import java.time.LocalTime

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
        val lesson = Lesson(
            dayOfWeek = dayOfWeek,
            week = week,
            lessonType = lessonType,
            startTime = startTime,
            location = location,
            subjectId = subjectId
        )
        checkIfExists(lesson)
        dao.insertLesson(lesson)
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
        val lesson = Lesson(
            id = id,
            dayOfWeek = dayOfWeek,
            week = week,
            lessonType = lessonType,
            startTime = startTime,
            location = location,
            subjectId = subjectId
        )
        checkIfExists(lesson)
        dao.updateLesson(lesson)
    }

    private suspend fun checkIfExists(lesson: Lesson) {

        val lessons = dao.getLessonsByWeek(lesson.week)
        val duration = ofMinutes(91)

        val lessonTime = LocalTime.parse(lesson.startTime)

        val lowerBound = lessonTime.minus(duration)
        val upperBound = lessonTime.plus(duration)

        val filteredLessons = lessons.filter {
            val itTime = LocalTime.parse(it.startTime)

            val isNotIdentical = it.id != lesson.id
            val isInRange = itTime.isAfter(lowerBound) && itTime.isBefore(upperBound)

            val isSameDay = it.dayOfWeek == lesson.dayOfWeek


            isInRange && isSameDay && isNotIdentical
        }

        if (filteredLessons.isNotEmpty())
            throw Exception("В даний проміжок часу вже є заняття. Оберіть інший час.")

    }

    suspend fun deleteLessonById(id: Long) {
        dao.deleteLessonById(id)
    }
}