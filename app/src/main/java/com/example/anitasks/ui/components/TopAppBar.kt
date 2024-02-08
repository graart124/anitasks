package com.example.anitasks.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.anitasks.ui.theme.AppTextStyle
import com.example.anitasks.ui.theme.PurpleLight


@Composable
fun TopAppBar(
    label: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = PurpleLight)
            .padding(16.dp)
    ) {
        Text(text = label, style = AppTextStyle.RobotoBold.sp32.copy(color = Color.White))
    }
}