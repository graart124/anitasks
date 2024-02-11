package com.example.anitasks.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.anitasks.R
import com.example.anitasks.screens.profile.components.LessonPieChart
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

    Scaffold(
        topBar = { TopAppBar(label = stringResource(R.string.profile)) },
        containerColor = Background
    ) {
        Column(
            modifier = Modifier.padding(
                top = it.calculateTopPadding(),
                bottom = it.calculateBottomPadding()
            )
        ) {
            UserInfoSection(user = state.user)
            Spacer(modifier = Modifier.height(24.dp))

            LessonPieChart(lessons = state.lessons, selectedLessonType = state.selectedLessonType)
        }
    }
}

