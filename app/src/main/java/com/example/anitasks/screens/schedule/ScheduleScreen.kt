package com.example.anitasks.screens.schedule

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination
@Composable
fun ScheduleScreen(
    navigator: DestinationsNavigator,
) {

    Box {
        Text(text = "Schedule")
    }
}