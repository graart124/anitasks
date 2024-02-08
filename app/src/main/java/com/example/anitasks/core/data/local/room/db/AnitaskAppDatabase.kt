package com.example.anitasks.core.data.local.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.anitasks.core.data.local.room.dao.LessonDao
import com.example.anitasks.core.data.local.room.dao.SubjectDao
import com.example.anitasks.core.data.model.Lesson
import com.example.anitasks.core.data.model.Subject


@Database(entities = [Lesson::class, Subject::class], exportSchema = false, version = 1)
abstract class AnitaskAppDatabase : RoomDatabase() {
    abstract val lessonDao: LessonDao
    abstract val subjectDao: SubjectDao
}
