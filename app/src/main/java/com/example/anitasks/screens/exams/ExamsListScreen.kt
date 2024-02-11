package com.example.anitasks.screens.exams

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import com.example.anitasks.R
import com.example.anitasks.core.data.model.Exam
import com.example.anitasks.core.util.OnLifecycleEvent
import com.example.anitasks.screens.destinations.AddExamScreenDestination
import com.example.anitasks.screens.exams.components.AddMarkDialog
import com.example.anitasks.screens.exams.components.ExamsList
import com.example.anitasks.ui.components.DeleteItemDialog
import com.example.anitasks.ui.components.ProgressDialog
import com.example.anitasks.ui.components.TopAppBar
import com.example.anitasks.ui.theme.Background
import com.example.anitasks.ui.theme.Blue
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun ExamsListScreen(
    navigator: DestinationsNavigator,
    viewModel: ExamsListViewModel = hiltViewModel()
) {
    val actionResult = viewModel.actionResult.collectAsState().value
    val state = viewModel.uiState.collectAsState().value
    val examToDelete = remember { mutableStateOf<Exam?>(null) }

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = actionResult) {
        if (!actionResult.info.isNullOrEmpty()) {
            snackbarHostState.showSnackbar(viewModel.actionResult.value.info!!)
        }
    }

    OnLifecycleEvent { _, event ->
        if (event == Lifecycle.Event.ON_RESUME) {
            viewModel.loadExams()
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            TopAppBar(
                label = stringResource(R.string.exams)
            )
        },
        containerColor = Background,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navigator.navigate(AddExamScreenDestination())
            }, containerColor = Blue, contentColor = Color.White, shape = CircleShape) {
                Image(
                    painter = painterResource(id = R.drawable.ic_bottom_add_lesson),
                    contentDescription = "add icon"
                )
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
        ) {
            if (state.loading) {
                ProgressDialog()
            } else {
                ExamsList(
                    exams = state.exams,
                    onSetExamMark = viewModel::selectExamToChangeMark,
                    onDeleteExam = { exam ->
                        examToDelete.value = exam
                    },
                    onEditExam = { exam ->
                        navigator.navigate(AddExamScreenDestination(exam = exam))
                    })
            }
        }
    }

    if (examToDelete.value!=null) {
        DeleteItemDialog(
            title = stringResource(R.string.delete_exam),
            subTitle = stringResource(R.string.sure_to_delete_exam),
            onDeleteClick = {
                viewModel.deleteExam(examToDelete.value!!)
                examToDelete.value!=null
            }, onDismissClick = {
                examToDelete.value = null
            }
        )
    }

    if(state.selectedExam!=null){
        AddMarkDialog(
            exam = state.selectedExam,
            onConfirmClick = viewModel::updateSelectedExam,
            onDismissClick = viewModel::clearSelectedExam,
            onMarkChanged = viewModel::onSelectedExamMarkChange
        )
    }
}