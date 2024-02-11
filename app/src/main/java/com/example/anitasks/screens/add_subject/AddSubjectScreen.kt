package com.example.anitasks.screens.add_subject

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.anitasks.R
import com.example.anitasks.core.data.model.Subject
import com.example.anitasks.ui.components.DeleteItemDialog
import com.example.anitasks.ui.components.ProgressDialog
import com.example.anitasks.ui.components.SaveDeleteButtons
import com.example.anitasks.ui.components.TextFieldWithIcon
import com.example.anitasks.ui.components.TopAppBar
import com.example.anitasks.ui.theme.Background
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Destination
@Composable
fun AddSubjectScreen(
    navigator: DestinationsNavigator,
    viewModel: AddSubjectViewModel = hiltViewModel(),
    subject: Subject? = null
) {
    val state = viewModel.uiState.collectAsState().value
    val actionResult = viewModel.actionResult.collectAsState().value

    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = subject) {
        viewModel.initSubject(subject)
    }

    LaunchedEffect(key1 = actionResult) {
        if (actionResult.success == true) {
            //global toast
            Toast.makeText(context, actionResult.info, Toast.LENGTH_SHORT).show()
            navigator.navigateUp()
        }

        if (!actionResult.info.isNullOrEmpty()) {
            coroutineScope.launch {
                viewModel.actionResult.value.info?.let { snackbarHostState.showSnackbar(it) }
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            TopAppBar(
                label = if (subject == null) stringResource(R.string.new_subject) else stringResource(
                    R.string.edit_subject
                ),
                navigator = navigator,
                backButtonAvailable = true
            )
        },
        containerColor = Background

    ) {
        Column(
            modifier = Modifier
                .padding(top = it.calculateTopPadding())
                .padding(horizontal = 12.dp, vertical = 18.dp)
        ) {
            TextFieldWithIcon(
                title = stringResource(id = R.string.subject_name),
                value = state.subjectName,
                hint = stringResource(R.string.subject_hint),
                iconId = R.drawable.ic_write,
                onValueChange = viewModel::onSubjectNameChanged
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextFieldWithIcon(
                title = stringResource(R.string.teacher_name),
                value = state.teacherName,
                hint = stringResource(R.string.teacher_hint),
                iconId = R.drawable.ic_write,
                onValueChange = viewModel::onTeacherNameChanged
            )
            Spacer(modifier = Modifier.height(24.dp))
            SaveDeleteButtons(onDeleteClick = {
                keyboardController?.hide()
                viewModel.onDeleteClick()
            }, onSaveClick = {
                keyboardController?.hide()
                viewModel.saveSubject()
            })
        }
    }

    if (state.showProgressDialog) {
        ProgressDialog()
    }

    if (state.showDeleteDialog) {
        DeleteItemDialog(
            title = stringResource(R.string.delete_subject),
            subTitle = stringResource(R.string.sure_to_delete_subject),
            onDeleteClick = {
                viewModel.deleteSubject()
            }, onDismissClick = {
                viewModel.dismissDeleteDialog()
            }
        )
    }
}