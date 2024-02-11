package com.example.anitasks.screens.profile

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import com.example.anitasks.R
import com.example.anitasks.core.util.OnLifecycleEvent
import com.example.anitasks.screens.profile.components.ExamsInfoSection
import com.example.anitasks.screens.profile.components.LessonInfoSection
import com.example.anitasks.screens.profile.components.UserInfoSection
import com.example.anitasks.ui.components.TopAppBar
import com.example.anitasks.ui.theme.Background
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsState().value

    OnLifecycleEvent { _, event ->
        if (event == Lifecycle.Event.ON_RESUME) {
            viewModel.loadLessons()
        }
    }

    Scaffold(
        topBar = { TopAppBar(label = stringResource(R.string.profile)) },
        containerColor = Background
    ) {
        Column(
            modifier = Modifier
                .padding(
                    top = it.calculateTopPadding(),
                    bottom = it.calculateBottomPadding()
                )
                .scrollable(state = rememberScrollState(), orientation = Orientation.Vertical)
        ) {
            UserInfoSection(user = state.user)
            Spacer(modifier = Modifier.height(8.dp))
            if (state.lessons.isNotEmpty()) {
                LessonInfoSection(
                    lesson = state.lessons,
                    selectedLessonType = state.selectedLessonType,
                    onLessonTypeClick = viewModel::onLessonTypeClick
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
            if (state.exams.isNotEmpty()) {
                ExamsInfoSection(exams = state.exams)
            }
        }
    }
}

