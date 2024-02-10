package com.example.anitasks.core.data.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.anitasks.core.data.model.Lesson


@Dao
interface LessonDao {
    @Query("SELECT * FROM lessons")
    suspend fun getAllLessons(): List<Lesson>

    @Query("SELECT * FROM lessons WHERE week = :week")
    suspend fun getLessonsByWeek(week:Int): List<Lesson>

    @Query("SELECT * FROM lessons WHERE id = :lessonId")
    suspend fun getLessonById(lessonId: Long): Lesson?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLesson(lesson: Lesson)

    @Update
    suspend fun updateLesson(lesson: Lesson)

    @Delete
    suspend fun deleteLesson(lesson: Lesson)
    @Query("DELETE FROM lessons WHERE id = :id")
    suspend fun deleteLessonById(id: Long)
}