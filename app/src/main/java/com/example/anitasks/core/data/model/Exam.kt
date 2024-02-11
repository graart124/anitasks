package com.example.anitasks.core.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Entity(
    tableName = "exams",
    foreignKeys = [ForeignKey(
        entity = Subject::class,
        parentColumns = ["subject_id"],
        childColumns = ["subject_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
@Parcelize
data class Exam(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(name = "start_time") var startTime: String, // "HH:mm"
    var date:LocalDate,
    var location: String?,
    @ColumnInfo(
        name = "subject_id",
        index = true
    ) var subjectId: Long,
    var mark:Float?,
    @Ignore var subject: Subject? = null
) : Parcelable {
    constructor() : this(
        id = 0,
        startTime = "00:00",
        location = null,
        date = LocalDate.now(),
        subjectId = 0,
        subject = null,
        mark = null
    )
}
