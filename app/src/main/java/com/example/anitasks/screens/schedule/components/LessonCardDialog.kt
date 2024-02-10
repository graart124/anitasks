package com.example.anitasks.screens.schedule.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.anitasks.R
import com.example.anitasks.core.data.model.Lesson
import com.example.anitasks.ui.theme.AppTextStyle
import com.example.anitasks.ui.theme.Background
import com.example.anitasks.ui.theme.NavBarColor
import com.example.anitasks.ui.theme.Primary


@Composable
fun LessonCardDialog(
    lesson: Lesson?,
    onDismissClick: () -> Unit,
    onEditLessonClick: ((Lesson) -> Unit)
) {

    if (lesson == null) return
    Dialog(
        onDismissRequest = onDismissClick,
        properties = DialogProperties(dismissOnClickOutside = false)
    ) {
        Surface(
            shape = RoundedCornerShape(7.dp),
            border = BorderStroke(5.dp, color = NavBarColor),
            color = Background,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(vertical = 24.dp, horizontal = 20.dp)) {
                Text(
                    text = "Предмет: ${lesson.subject?.name}",
                    style = AppTextStyle.RobotoRegular.sp20.copy(color = Color.White)
                )
                Spacer(modifier = Modifier.height(28.dp))
                Text(
                    text = "Урок: ${lesson.lessonType.displayName}",
                    style = AppTextStyle.RobotoRegular.sp20.copy(color = Color.White)
                )
                Spacer(modifier = Modifier.height(28.dp))
                Text(
                    text = "Час: ${lesson.dayOfWeek.displayName} ${lesson.startTime}",
                    style = AppTextStyle.RobotoRegular.sp20.copy(color = Color.White)
                )
                Spacer(modifier = Modifier.height(48.dp))
                Row() {
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = { onEditLessonClick(lesson) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Primary
                        ),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.toEdit),
                            style = AppTextStyle.RobotoRegular.sp14.copy(color = Color.White)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Image(
                            modifier = Modifier.size(18.dp),
                            painter = painterResource(id = R.drawable.ic_write),
                            contentDescription = "delete icon"
                        )
                    }
                    Spacer(modifier = Modifier.width(17.dp))
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = onDismissClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Primary
                        ),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(R.string.dismiss),
                                style = AppTextStyle.RobotoRegular.sp14.copy(color = Color.White),
                            )
                        }
                    }
                }
            }
        }
    }
}
