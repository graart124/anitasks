package com.example.anitasks.screens.add_lesson.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.anitasks.ui.theme.AppTextStyle
import com.example.anitasks.ui.theme.PurpleDark
import com.example.anitasks.ui.theme.UnselectedNavBarItemColor


@Composable
fun AddLessonItem(
    title: String,
    value: String?,
    hint: String,
    @DrawableRes iconId: Int,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = PurpleDark, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 4.dp, vertical = 6.dp)
    ) {
        Text(text = title, style = AppTextStyle.RobotoBold.sp14.copy(color = Color.White))
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = onClick,
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(0.5.dp, UnselectedNavBarItemColor),
            contentPadding = PaddingValues(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = value ?: hint,
                    style = AppTextStyle.RobotoRegular.sp14.copy(color = Color.White)
                )
                Image(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id =iconId),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}