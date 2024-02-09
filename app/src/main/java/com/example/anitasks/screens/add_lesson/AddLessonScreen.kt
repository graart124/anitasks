package com.example.anitasks.screens.add_lesson

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.anitasks.R
import com.example.anitasks.core.data.model.Lesson
import com.example.anitasks.core.data.model.Subject
import com.example.anitasks.screens.add_lesson.components.AddLessonItem
import com.example.anitasks.screens.add_lesson.components.LessonTypes
import com.example.anitasks.screens.add_lesson.components.NumberOfWeek
import com.example.anitasks.screens.destinations.SubjectListScreenDestination
import com.example.anitasks.ui.components.SaveDeleteButtons
import com.example.anitasks.ui.components.TextFieldWithIcon
import com.example.anitasks.ui.components.TopAppBar
import com.example.anitasks.ui.theme.AppTextStyle
import com.example.anitasks.ui.theme.Background
import com.example.anitasks.ui.theme.Primary
import com.example.anitasks.ui.theme.PurpleDark
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.NavResult
import com.ramcosta.composedestinations.result.ResultRecipient
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.time.TimePickerDefaults
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalTime

@Destination
@Composable
fun AddLessonScreen(
    navigator: DestinationsNavigator,
    resultSubjectRecipient: ResultRecipient<SubjectListScreenDestination, Subject?>,
    viewModel: AddLessonViewModel = hiltViewModel(),
    lesson: Lesson?
) {
    val state = viewModel.uiState.collectAsState().value
    val context = LocalContext.current
    val timeDialogState = rememberMaterialDialogState()

    resultSubjectRecipient.onNavResult { result ->
        when (result) {
            is NavResult.Canceled -> {}
            is NavResult.Value -> {
                viewModel.selectSubject(result.value)
            }
        }
    }

    Scaffold(
        topBar = { TopAppBar(label = stringResource(R.string.new_lesson)) },
        containerColor = Background

    ) {
        Column(
            modifier = Modifier
                .scrollable(state = rememberScrollState(), orientation = Orientation.Vertical)
                .padding(top = it.calculateTopPadding())
                .padding(horizontal = 12.dp, vertical = 18.dp)
        ) {
            AddLessonItem(
                title = stringResource(R.string.subject_name),
                value = state.subject?.name,
                hint = stringResource(R.string.choose_subject),
                iconId = R.drawable.ic_arrow_right,
                onClick = {
                    navigator.navigate(SubjectListScreenDestination())
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextFieldWithIcon(
                title = stringResource(R.string.lesson_location),
                value = state.location,
                hint = stringResource(R.string.location_hint),
                iconId = R.drawable.ic_location,
                onValueChange = viewModel::onLocationChanged
            )
            Spacer(modifier = Modifier.height(16.dp))
            LessonTypes(
                selectedLessonType = state.lessonType,
                onSelect = viewModel::selectLessonType
            )
            Spacer(modifier = Modifier.height(16.dp))
            AddLessonItem(
                title = stringResource(R.string.day_of_week),
                value = state.dayOfWeek?.displayName,
                hint = stringResource(R.string.pick_day),
                iconId = R.drawable.ic_pick_day,
                onClick = {
                    viewModel.showDayOfWeekDialog(context = context)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            AddLessonItem(
                title = stringResource(R.string.lesson_start_time),
                value = state.startTime,
                hint = stringResource(R.string.pick_time),
                iconId = R.drawable.ic_pick_time,
                onClick = {
                    timeDialogState.show()
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            NumberOfWeek(selectedWeek = state.week, onSelect = viewModel::selectWeek)
            Spacer(modifier = Modifier.height(24.dp))
            SaveDeleteButtons(onDeleteClick = {

            }, onSaveClick = {

            })
        }
    }

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
            initialTime = LocalTime.parse(state.startTime ?: "08:00"),
            timeRange = LocalTime.of(6, 0)..LocalTime.of(22, 30)
        ) {
            viewModel.selectStartTime(it)
        }
    }
}
