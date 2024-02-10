package com.example.anitasks.screens.schedule

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import com.example.anitasks.R
import com.example.anitasks.core.data.model.Lesson
import com.example.anitasks.core.util.OnLifecycleEvent
import com.example.anitasks.screens.destinations.AddLessonScreenDestination
import com.example.anitasks.screens.schedule.components.CalendarView
import com.example.anitasks.screens.schedule.components.LessonCardDialog
import com.example.anitasks.ui.components.ProgressDialog
import com.example.anitasks.ui.components.TopAppBar
import com.example.anitasks.ui.theme.Background
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination
@Composable
fun ScheduleScreen(
    navigator: DestinationsNavigator,
    viewModel: ScheduleViewModel = hiltViewModel()
) {
    val selectedLesson = remember { mutableStateOf<Lesson?>(null) }
    val actionResult = viewModel.actionResult.collectAsState().value
    val state = viewModel.uiState.collectAsState().value
    val context = LocalContext.current

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = actionResult) {
        if (!actionResult.info.isNullOrEmpty()) {
            snackbarHostState.showSnackbar(viewModel.actionResult.value.info!!)
        }
    }

    OnLifecycleEvent { _, event ->
        if (event == Lifecycle.Event.ON_RESUME) {
            viewModel.loadLessons()
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            TopAppBar(
                label = stringResource(R.string.schedule)
            )
        },
        containerColor = Background
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
        ) {
            if (state.loading) {
                ProgressDialog()
            } else {
                CalendarView(
                    week = state.currentWeek,
                    lessons = state.lessons,
                    onLessonClick = { lesson ->
                        selectedLesson.value = lesson
                    }
                )
            }
        }
    }

    LessonCardDialog(
        lesson = selectedLesson.value,
        onDismissClick = { selectedLesson.value = null },
        onEditLessonClick = { lesson ->
            navigator.navigate(AddLessonScreenDestination(lesson))
        }
    )

}