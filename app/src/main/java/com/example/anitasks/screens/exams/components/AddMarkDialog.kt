package com.example.anitasks.screens.exams.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.anitasks.R
import com.example.anitasks.core.data.model.Exam
import com.example.anitasks.ui.components.TextFieldWithIcon
import com.example.anitasks.ui.theme.AppTextStyle
import com.example.anitasks.ui.theme.Background
import com.example.anitasks.ui.theme.NavBarColor
import com.example.anitasks.ui.theme.Primary
import com.example.anitasks.ui.theme.Red


@Composable
fun AddMarkDialog(
    exam: Exam,
    onConfirmClick: () -> Unit,
    onDismissClick: () -> Unit,
    onMarkChanged: (String) -> Unit
) {

    Dialog(
        onDismissRequest = onDismissClick,
    ) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(5.dp, color = NavBarColor),
            color = Background,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(vertical = 24.dp, horizontal = 20.dp)) {
                TextFieldWithIcon(
                    title = stringResource(R.string.setup_mark),
                    value = if(exam.mark == null) "" else exam.mark.toString(),
                    hint = stringResource(R.string.mark_must_be_here),
                    iconId = R.drawable.ic_write,
                    onValueChange = onMarkChanged,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.height(32.dp))
                Row {
                    TextButton(
                        modifier = Modifier.weight(1f),
                        onClick = {
                            onDismissClick()
                        },
                        contentPadding = PaddingValues(vertical = 14.dp),

                        ) {
                        Text(
                            text = stringResource(R.string.dismiss),
                            style = AppTextStyle.RobotoRegular.sp14.copy(color = Red)
                        )
                    }
                    Spacer(modifier = Modifier.width(17.dp))
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = {
                            onConfirmClick()
                        },
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
                                text = stringResource(R.string.save),
                                style = AppTextStyle.RobotoRegular.sp14.copy(color = Color.White),
                            )
                        }
                    }

                }
            }
        }
    }
}
