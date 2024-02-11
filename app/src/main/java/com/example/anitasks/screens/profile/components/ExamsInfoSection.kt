package com.example.anitasks.screens.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.anitasks.core.data.model.Exam
import com.example.anitasks.ui.theme.AppTextStyle
import com.example.anitasks.ui.theme.PurpleDark
import com.example.anitasks.ui.theme.PurpleLight
import java.time.LocalDate

@Composable
fun ExamsInfoSection(
    exams: List<Exam>
) {
    val scheduledExams = exams.filter { it.date.isAfter(LocalDate.now()) }
    val completedExams = exams.filter { it.date.isBefore(LocalDate.now()) }
    val averageMark = calculateAverageMark(exams)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .padding(top = 8.dp)
            .background(color = PurpleDark, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp),

    ) {
        Text(
            text = "Іспити",
            style = AppTextStyle.RobotoSemiBold.sp28.copy(color = Color.White)
        )
        val string = averageMark ?:" "
        Spacer(modifier = Modifier.height(24.dp))
        ExamInfo(name = "Заплановані іспити: ", value = "${scheduledExams.size}", color = Color.Red)
        Spacer(modifier = Modifier.height(24.dp))
        ExamInfo(name = "Завершені іспити іспити: ", value = "${completedExams.size}", color = Color.Green)
        Spacer(modifier = Modifier.height(24.dp))
        ExamInfo(name = "Середній бал: ", value = string.toString(), color = PurpleLight)
        Spacer(modifier = Modifier.height(8.dp))
    }

}

@Composable
fun ExamInfo(name: String, value: String, color: Color){

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = name,
            style = AppTextStyle.RobotoSemiBold.sp20.copy(color = Color.White)
        )
        Text(
            text = value,
            style = AppTextStyle.RobotoSemiBold.sp20.copy(color = color)
        )
    }
}


fun calculateAverageMark(exams: List<Exam>): Float? {
    var totalMark = 0f
    var examCount = 0
    for (exam in exams) {
        exam.mark?.let { mark ->
            totalMark += mark
            examCount++
        }
    }

    return if (examCount > 0) {
        totalMark / examCount.toFloat()
    } else {
        null
    }
}