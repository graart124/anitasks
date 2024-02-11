package com.example.anitasks.screens.add_exam

import android.widget.Toast
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.anitasks.R
import com.example.anitasks.core.data.model.Exam
import com.example.anitasks.core.data.model.Subject
import com.example.anitasks.screens.destinations.SubjectListScreenDestination
import com.example.anitasks.ui.components.AddItemSection
import com.example.anitasks.ui.components.DatePickerDialog
import com.example.anitasks.ui.components.DeleteItemDialog
import com.example.anitasks.ui.components.SaveDeleteButtons
import com.example.anitasks.ui.components.TextFieldWithIcon
import com.example.anitasks.ui.components.TimePickerDialog
import com.example.anitasks.ui.components.TopAppBar
import com.example.anitasks.ui.theme.Background
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.NavResult
import com.ramcosta.composedestinations.result.ResultRecipient
import com.vanpra.composematerialdialogs.rememberMaterialDialogState


@Destination
@Composable
fun AddExamScreen(
    navigator: DestinationsNavigator,
    resultSubjectRecipient: ResultRecipient<SubjectListScreenDestination, Subject?>,
    viewModel: AddExamViewModel = hiltViewModel(),
    exam: Exam? = null
) {
    val state = viewModel.uiState.collectAsState().value
    val actionResult = viewModel.actionResult.collectAsState().value

    val context = LocalContext.current
    val timeDialogState = rememberMaterialDialogState()
    val dateDialogState = rememberMaterialDialogState()

    val snackbarHostState = remember { SnackbarHostState() }
    val keyboardController = LocalSoftwareKeyboardController.current

    resultSubjectRecipient.onNavResult { result ->
        when (result) {
            is NavResult.Canceled -> {}
            is NavResult.Value -> {
                viewModel.selectSubject(result.value)
            }
        }
    }

    LaunchedEffect(key1 = exam) {
        viewModel.initExam(exam)
    }

    LaunchedEffect(key1 = actionResult) {
        if (actionResult.success == true) {
            //global toast
            Toast.makeText(context, actionResult.info, Toast.LENGTH_SHORT).show()
            navigator.navigateUp()
        }

        if (!actionResult.info.isNullOrEmpty()) {
            viewModel.actionResult.value.info?.let { snackbarHostState.showSnackbar(it) }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            TopAppBar(
                label = if (exam == null) stringResource(R.string.new_exam) else stringResource(R.string.edit_exam),
                navigator = navigator,
                backButtonAvailable = true
            )
        },
        containerColor = Background
    ) {
        Column(
            modifier = Modifier
                .scrollable(state = rememberScrollState(), orientation = Orientation.Vertical)
                .padding(top = it.calculateTopPadding())
                .padding(horizontal = 12.dp, vertical = 18.dp)
        ) {
            AddItemSection(
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
            AddItemSection(
                title = stringResource(R.string.exam_date),
                value = if (state.date != null) state.date.toString() else null,
                hint = stringResource(R.string.pick_date),
                iconId = R.drawable.ic_pick_date,
                onClick = {
                    dateDialogState.show()
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            AddItemSection(
                title = stringResource(R.string.exam_time),
                value = state.startTime,
                hint = stringResource(R.string.pick_time),
                iconId = R.drawable.ic_pick_time,
                onClick = {
                    timeDialogState.show()
                }
            )
            Spacer(modifier = Modifier.height(28.dp))
            SaveDeleteButtons(onDeleteClick = {
                keyboardController?.hide()
                viewModel.onDeleteClick()
            }, onSaveClick = {
                keyboardController?.hide()
                viewModel.saveExam()
            })
        }
    }

    TimePickerDialog(
        timeDialogState = timeDialogState,
        startTime = state.startTime,
        onSelectTime = viewModel::selectStartTime
    )
    DatePickerDialog(
        dateDialogState = dateDialogState,
        date = state.date,
        onSelectDate = viewModel::selectDate
    )

    if (state.showDeleteDialog) {
        DeleteItemDialog(
            title = "Видалити екзамен?",
            subTitle = "Аніта попереджає, пілся видалення екзамен не буде враховуватись у статистику",
            onDeleteClick = {
                viewModel.deleteExam()
            }, onDismissClick = {
                viewModel.dismissDeleteDialog()
            }
        )
    }
}