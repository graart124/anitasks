package com.example.anitasks.core.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subjects")
data class Subject(
    @PrimaryKey(autoGenerate = true) val id: Long=0,
    @ColumnInfo(name = "subject_name") val name: String,
    @ColumnInfo(name = "teacher_name") val teacherName: String?
)

