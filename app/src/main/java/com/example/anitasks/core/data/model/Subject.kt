package com.example.anitasks.core.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subjects")
data class Subject(
    @ColumnInfo(name = "subject_id") @PrimaryKey(autoGenerate = true) var id: Long=0,
    @ColumnInfo(name = "subject_name") var name: String,
    @ColumnInfo(name = "teacher_name") var teacherName: String?
)

