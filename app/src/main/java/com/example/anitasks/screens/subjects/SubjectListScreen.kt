package com.example.anitasks.screens.subjects

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import com.example.anitasks.R
import com.example.anitasks.core.data.model.Subject
import com.example.anitasks.core.util.OnLifecycleEvent
import com.example.anitasks.screens.destinations.AddSubjectScreenDestination
import com.example.anitasks.screens.subjects.components.SubjectList
import com.example.anitasks.ui.components.ProgressDialog
import com.example.anitasks.ui.components.TopAppBar
import com.example.anitasks.ui.theme.Background
import com.example.anitasks.ui.theme.Blue
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.ResultBackNavigator
import kotlinx.coroutines.launch


@Destination
@Composable
fun SubjectListScreen(
    navigator: DestinationsNavigator,
    resultNavigator: ResultBackNavigator<Subject?>,
    viewModel: SubjectListViewModel = hiltViewModel()
) {
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
            viewModel.loadSubjects()
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            TopAppBar(
                label = stringResource(R.string.subjects),
                navigator = navigator,
                backButtonAvailable = true
            )
        },
        containerColor = Background,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navigator.navigate(AddSubjectScreenDestination())
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
            }else {
                SubjectList(
                    subjects = state.subjects,
                    onChooseSubject = { subject ->
                        resultNavigator.navigateBack(result = subject)
                    },
                    onDeleteSubject = { subject ->
                        viewModel.onDeleteSubject(subject = subject, context = context)
                    },
                    onEditSubject = { subject ->
                        navigator.navigate(AddSubjectScreenDestination(subject = subject))
                    }
                )
            }
        }

    }


}