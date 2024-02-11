package com.example.anitasks.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.anitasks.ui.theme.AppTextStyle
import com.example.anitasks.ui.theme.Background
import com.example.anitasks.ui.theme.Primary
import com.example.anitasks.ui.theme.PurpleDark
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import java.time.LocalDate


@Composable
fun DatePickerDialog(
    dateDialogState: MaterialDialogState,
    date: LocalDate?,
    onSelectDate: (LocalDate) -> Unit
) {
    MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton(
                text = "Confirm",
                textStyle = AppTextStyle.RobotoRegular.sp14.copy(color = Primary)
            )
            negativeButton(
                text = "Cancel",
                textStyle = AppTextStyle.RobotoRegular.sp14.copy(color = PurpleDark)
            )
        },
        backgroundColor = Background
    ) {

        datepicker(
            colors = DatePickerDefaults.colors(
                dateActiveBackgroundColor = Primary,
                dateInactiveBackgroundColor = PurpleDark,
                calendarHeaderTextColor = Color.White,
                headerTextColor = Color.White

            ),
            initialDate = date ?: LocalDate.now(),
            onDateChange = onSelectDate,
            yearRange = LocalDate.now().year - 1..LocalDate.now().year + 1
        )
    }
}