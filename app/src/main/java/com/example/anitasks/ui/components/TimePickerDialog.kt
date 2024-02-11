package com.example.anitasks.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.anitasks.ui.theme.AppTextStyle
import com.example.anitasks.ui.theme.Background
import com.example.anitasks.ui.theme.Primary
import com.example.anitasks.ui.theme.PurpleDark
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.time.TimePickerDefaults
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import java.time.LocalTime

@Composable
fun TimePickerDialog(
    timeDialogState: MaterialDialogState,
    startTime: String?,
    onSelectTime: (LocalTime) -> Unit
) {

    MaterialDialog(
        dialogState = timeDialogState,
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

        timepicker(
            is24HourClock = true,
            colors = TimePickerDefaults.colors(
                activeBackgroundColor = Primary,
                inactiveBackgroundColor = PurpleDark,
                activeTextColor = Color.White,
                inactiveTextColor = Color.White,
                headerTextColor = Color.White

            ),
            initialTime = LocalTime.parse(startTime ?: "08:00"),
            timeRange = LocalTime.of(6, 0)..LocalTime.of(22, 30),
            onTimeChange = onSelectTime
        )
    }
}
