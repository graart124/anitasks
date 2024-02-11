package com.example.anitasks.screens.profile.components

import android.graphics.Rect
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.anitasks.core.data.model.Lesson
import com.example.anitasks.core.data.model.LessonType
import com.example.anitasks.core.data.model.LessonType.LECTURE
import com.example.anitasks.core.data.model.LessonType.PRACTICAL
import com.example.anitasks.core.data.model.LessonType.SEMINAR
import com.example.anitasks.ui.theme.LectureColor
import com.example.anitasks.ui.theme.PracticalColor
import com.example.anitasks.ui.theme.PurpleDark
import com.example.anitasks.ui.theme.SeminarColor
import com.example.anitasks.ui.theme.getColor

@Composable
fun LessonPieChart(lessons: List<Lesson>, selectedLessonType: LessonType?) {

    val lectureCount = lessons.count { it.lessonType == LECTURE }
    val seminarCount = lessons.count { it.lessonType == SEMINAR }
    val practicalCount = lessons.count { it.lessonType == PRACTICAL }

    val totalCount = lessons.size
    val text:String = when(selectedLessonType) {
        LECTURE -> lectureCount.toString()
        SEMINAR -> seminarCount.toString()
        PRACTICAL -> practicalCount.toString()
        null -> ""
    }

    val textColor = selectedLessonType?.getColor()?:Color.White

    Canvas(modifier = Modifier.size(150.dp)) {
        val radius = size.minDimension / 2

        val startAngle = 0f
        val lectureSweepAngle = 360f * (lectureCount.toFloat() / totalCount)
        val seminarSweepAngle = 360f * (seminarCount.toFloat() / totalCount)
        val practicalSweepAngle = 360f * (practicalCount.toFloat() / totalCount)

        drawArc(
            color = LectureColor,
            startAngle = startAngle,
            sweepAngle = lectureSweepAngle,
            useCenter = true,
            topLeft = Offset.Zero,
            size = Size(radius * 2, radius * 2)
        )

        drawArc(
            color = SeminarColor,
            startAngle = startAngle + lectureSweepAngle,
            sweepAngle = seminarSweepAngle,
            useCenter = true,
            topLeft = Offset.Zero,
            size = Size(radius * 2, radius * 2)
        )

        drawArc(
            color = PracticalColor,
            startAngle = startAngle + lectureSweepAngle + seminarSweepAngle,
            sweepAngle = practicalSweepAngle,
            useCenter = true,
            topLeft = Offset.Zero,
            size = Size(radius * 2, radius * 2)
        )

        drawCircle(
            color = PurpleDark,
            radius = radius - 10.dp.toPx(),
            center = Offset(radius, radius)
        )


        drawIntoCanvas { canvas ->
            val textPaint = Paint().asFrameworkPaint().apply {
                color = textColor.toArgb()
                textSize = 24.sp.toPx()
            }
            val textBounds = Rect()
            textPaint.getTextBounds(text, 0, text.length, textBounds)
            canvas.nativeCanvas.drawText(
                text,
                radius - textBounds.exactCenterX(),
                radius - textBounds.exactCenterY(),
                textPaint
            )
        }
    }
}
