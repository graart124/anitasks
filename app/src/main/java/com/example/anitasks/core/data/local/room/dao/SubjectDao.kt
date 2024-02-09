package com.example.anitasks.core.data.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.anitasks.core.data.model.Subject

@Dao
interface SubjectDao {
    @Query("SELECT * FROM subjects")
    suspend fun getAllSubjects(): List<Subject>

    @Query("SELECT * FROM subjects WHERE subject_id = :subjectId")
    suspend fun getSubjectById(subjectId: Long): Subject?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubject(subject: Subject)

    @Update
    suspend fun updateSubject(subject: Subject)

    @Delete
    suspend fun deleteSubject(subject: Subject)
    @Query("DELETE FROM subjects WHERE subject_id = :id")
    suspend fun deleteSubjectById(id: Long)
}
