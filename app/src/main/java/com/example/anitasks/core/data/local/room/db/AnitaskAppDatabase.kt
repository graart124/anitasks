package com.example.anitasks.core.data.local.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.anitasks.core.data.local.room.convertors.LocalDateConvertor
import com.example.anitasks.core.data.local.room.dao.ExamDao
import com.example.anitasks.core.data.local.room.dao.LessonDao
import com.example.anitasks.core.data.local.room.dao.SubjectDao
import com.example.anitasks.core.data.model.Exam
import com.example.anitasks.core.data.model.Lesson
import com.example.anitasks.core.data.model.Subject


@Database(entities = [Lesson::class, Subject::class,Exam::class], exportSchema = false, version = 1)
@TypeConverters(LocalDateConvertor::class)
abstract class AnitaskAppDatabase : RoomDatabase() {
    abstract val lessonDao: LessonDao
    abstract val subjectDao: SubjectDao
    abstract val examDao:ExamDao
}
