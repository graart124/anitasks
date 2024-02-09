package com.example.anitasks.screens.subjects.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.anitasks.R
import com.example.anitasks.core.data.model.Subject
import com.example.anitasks.ui.theme.AppTextStyle
import com.example.anitasks.ui.theme.PurpleDark
import com.example.anitasks.ui.theme.Red


@Composable
fun SubjectList(
    subjects: List<Subject>,
    onChooseSubject: (Subject) -> Unit,
    onDeleteSubject: (Subject) -> Unit,
    onEditSubject: (Subject) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 10.dp, end = 10.dp, bottom = 100.dp, top = 18.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(subjects) { subject ->
            SubjectItem(
                subject,
                onChooseSubject,
                onDeleteSubject,
                onEditSubject
            )
        }
    }
}

@Composable
fun SubjectItem(
    subject: Subject,
    onChooseSubject: (Subject) -> Unit,
    onDeleteSubject: (Subject) -> Unit,
    onEditSubject: (Subject) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onChooseSubject(subject)
            }
            .background(color = PurpleDark, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp, bottom = 10.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = subject.name,
                style = AppTextStyle.RobotoSemiBold.sp18.copy(color = Color.White),
                maxLines = 1,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = subject.teacherName?:"",
                style = AppTextStyle.RobotoRegular.sp16.copy(color = Color.White),
                maxLines = 1,

            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.clickable {
                  onEditSubject(subject)
                },
                painter = painterResource(id = R.drawable.ic_edit),
                contentDescription = "edit icon"
            )
            Spacer(modifier = Modifier.height(14.dp))
            Image(
                modifier = Modifier.clickable {
                    onDeleteSubject(subject)
                },
                colorFilter = ColorFilter.tint(color = Red),
                painter = painterResource(id = R.drawable.ic_delete),
                contentDescription = "delete icon"
            )
        }
    }

}


