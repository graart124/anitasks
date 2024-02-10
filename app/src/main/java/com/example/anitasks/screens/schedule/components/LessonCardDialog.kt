package com.example.anitasks.screens.schedule.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.anitasks.core.data.model.Lesson


@Composable
fun LessonCardDialog(
    lesson: Lesson?,
    onDismissClick: () -> Unit,
    onEditLessonClick: ((Lesson) -> Unit)
) {
    if (lesson == null) return
    Dialog(
        onDismissRequest = onDismissClick,
        properties = DialogProperties(dismissOnClickOutside = false)
    ) {
        //todo - ui code
    }
}
