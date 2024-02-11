package com.example.anitasks.core.data.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.anitasks.core.data.model.Exam

@Dao
interface ExamDao {
    @Query("SELECT * FROM exams")
    suspend fun getAllExams(): List<Exam>

    @Query("SELECT * FROM exams WHERE id = :examId")
    suspend fun getExamById(examId: Long): Exam?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExam(exam: Exam)

    @Update
    suspend fun updateExam(exam: Exam)

    @Delete
    suspend fun deleteExam(exam: Exam)

    @Query("DELETE FROM exams WHERE id = :examId")
    suspend fun deleteExamById(examId: Long)
}