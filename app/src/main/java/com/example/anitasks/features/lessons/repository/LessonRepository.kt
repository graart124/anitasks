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
        dao.updateLesson(lesson)
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

    private suspend fun checkIfExists(lesson: Lesson){

        val lessons = dao.getLessonsByWeek(lesson.week)
        // Створіть змінну для тривалості 1.5 години
        val duration = ofMinutes(91)

// Створіть змінну для часу початку заданого уроку
        val lessonTime = LocalTime.parse (lesson.startTime)

// Отримайте межі діапазону часу
        val lowerBound = lessonTime.minus (duration)
        val upperBound = lessonTime.plus (duration)

// Знайдіть уроки, які відповідають критеріям
        val filteredLessons = lessons.filter {
            // Перетворіть час початку кожного уроку на об'єкт LocalTime
            val itTime = LocalTime.parse (it.startTime)

            val isNotIdentical = it.id != lesson.id
            // Перевірте, чи знаходиться час початку між межами діапазону
            val isInRange = itTime.isAfter (lowerBound) && itTime.isBefore (upperBound)

            // Перевірте, чи співпадає день тижня
            val isSameDay = it.dayOfWeek == lesson.dayOfWeek

            // Поверніть true, якщо обидві умови виконуються
            isInRange && isSameDay && isNotIdentical
        }

        if(filteredLessons.isNotEmpty())
            throw Exception("В даний проміжок часу вже є заняття. Оберіть інший час.")

    }
    suspend fun deleteLessonById(id: Long) {
        dao.deleteLessonById(id)
    }
}