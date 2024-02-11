package com.example.anitasks.screens.exams.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.anitasks.R
import com.example.anitasks.core.data.model.Exam
import com.example.anitasks.ui.theme.AddMarkColor
import com.example.anitasks.ui.theme.AppTextStyle
import com.example.anitasks.ui.theme.MarkColor
import com.example.anitasks.ui.theme.PurpleDark
import com.example.anitasks.ui.theme.Red
import com.example.anitasks.ui.theme.UnselectedNavBarItemColor
import java.time.LocalDate

@Composable
fun ExamsList(
    exams: List<Exam>,
    onSetExamMark: (Exam) -> Unit,
    onDeleteExam: (Exam) -> Unit,
    onEditExam: (Exam) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 10.dp, end = 10.dp, bottom = 100.dp, top = 18.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(exams) { exam ->
            ExamItem(
                exam,
                onSetExamMark,
                onDeleteExam,
                onEditExam
            )
        }
    }
}

@Composable
fun ExamItem(
    exam: Exam,
    onSetExamMark: (Exam) -> Unit,
    onDeleteExam: (Exam) -> Unit,
    onEditExam: (Exam) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = PurpleDark, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp, bottom = 10.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = exam.subject?.name ?: "",
                style = AppTextStyle.RobotoSemiBold.sp18.copy(color = Color.White),
                maxLines = 1,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = exam.date.toString(),
                style = AppTextStyle.RobotoRegular.sp16.copy(color = Color.White),
                maxLines = 1,
            )
            if (exam.location!=null) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = exam.location!!,
                    style = AppTextStyle.RobotoRegular.sp16.copy(color = Color.White),
                    maxLines = 1,
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Row {
                Image(
                    modifier = Modifier.clickable {
                        onEditExam(exam)
                    },
                    painter = painterResource(id = R.drawable.ic_edit),
                    contentDescription = "edit icon"
                )
                Spacer(modifier = Modifier.width(14.dp))
                Image(
                    modifier = Modifier.clickable {
                        onDeleteExam(exam)
                    },
                    colorFilter = ColorFilter.tint(color = Red),
                    painter = painterResource(id = R.drawable.ic_delete),
                    contentDescription = "delete icon"
                )
            }
            Spacer(modifier = Modifier.height(17.dp))
            if (exam.mark != null) {
                Box(
                    modifier = Modifier
                        .border(
                            BorderStroke(0.5.dp, UnselectedNavBarItemColor),
                            RoundedCornerShape(8.dp)
                        )
                        .clickable {
                            onSetExamMark(exam)
                        }
                        .padding(8.dp),
                )
                {
                    Text(
                        text = exam.mark.toString(),
                        style = AppTextStyle.RobotoRegular.sp16.copy(color = MarkColor),
                        maxLines = 1,
                    )
                }

            } else if (exam.date.isBefore(LocalDate.now())) {
                OutlinedButton(
                    modifier = Modifier,
                    onClick = {
                        onSetExamMark(exam)
                    },
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(0.5.dp, UnselectedNavBarItemColor),
                    contentPadding = PaddingValues(8.dp)
                )
                {
                    Text(
                        text = stringResource(R.string.set_mark),
                        style = AppTextStyle.RobotoRegular.sp10.copy(color = AddMarkColor),
                        maxLines = 1,
                    )
                }
            }
        }


    }

}