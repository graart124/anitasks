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
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(name = "day_of_week") var dayOfWeek: DayOfWeek,
    var week: Int,
    @ColumnInfo(name = "lesson_type") var lessonType: LessonType,
    @ColumnInfo(name = "start_time") var startTime: String, // "HH:mm"
    var location: String?,
    @ColumnInfo(
        name = "subject_id",
        index = true
    ) var subjectId: Int,
    @Ignore var subject: Subject? = null
){
    constructor() : this(
        id = 0,
        dayOfWeek = DayOfWeek.MONDAY,
        week = 1,
        lessonType = LessonType.LECTURE,
        startTime = "00:00",
        location = null,
        subjectId = 0,
        subject = null
    )
}

enum class DayOfWeek {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
}

enum class LessonType {
    LECTURE, SEMINAR, PRACTICAL
}