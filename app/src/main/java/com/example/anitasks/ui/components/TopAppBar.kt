package com.example.anitasks.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.anitasks.R
import com.example.anitasks.ui.theme.AppTextStyle
import com.example.anitasks.ui.theme.PurpleLight
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Composable
fun TopAppBar(
    label: String,
    backButtonAvailable: Boolean = false,
    navigator: DestinationsNavigator? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = PurpleLight)
            .padding(16.dp)
    ) {
        Box(modifier = Modifier.size(40.dp)) {
            if (backButtonAvailable) {
                Image(
                    modifier = Modifier
                        .size(40.dp)
                        .rotate(180f)
                        .clickable {
                            navigator?.navigateUp()
                        },
                    colorFilter = ColorFilter.tint(color = Color.White),
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = "back button"
                )
            }
        }
        Text(
            text = label,
            style = AppTextStyle.RobotoBold.sp32.copy(color = Color.White),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 40.dp)
        )
    }
}