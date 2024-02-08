package com.example.anitasks.core.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(
    tableName = "lessons",
    foreignKeys = [ForeignKey(
        entity = Subject::class,
        parentColumns = ["subject_id"],
        childColumns = ["subject_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Lesson(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "day_of_week") val dayOfWeek: DayOfWeek,
    val week: Int,
    @ColumnInfo(name = "lesson_type") val lessonType: LessonType,
    @ColumnInfo(name = "start_time") val startTime: String, // "HH:mm"
    val location: String?,
    @ColumnInfo(
        name = "subject_id",
        index = true
    ) val subjectId: Int,
    @Ignore val subject: Subject? = null
)

enum class DayOfWeek {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
}

enum class LessonType {
    LECTURE, SEMINAR, PRACTICAL
}