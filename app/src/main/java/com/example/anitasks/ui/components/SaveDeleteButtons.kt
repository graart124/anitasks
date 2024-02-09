package com.example.anitasks.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.anitasks.R
import com.example.anitasks.ui.theme.AppTextStyle
import com.example.anitasks.ui.theme.Primary
import com.example.anitasks.ui.theme.Red


@Composable
fun SaveDeleteButtons(onDeleteClick: () -> Unit, onSaveClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        TextButton(
            modifier = Modifier.weight(1f),
            onClick = onDeleteClick,
            contentPadding = PaddingValues(vertical = 14.dp),

            ) {
            Image(
                painter = painterResource(id = R.drawable.ic_delete),
                contentDescription = "delete icon"
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = stringResource(R.string.delete),
                style = AppTextStyle.RobotoRegular.sp14.copy(color = Red)
            )
        }
        Button(
            modifier = Modifier.weight(1f),
            onClick = onSaveClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Primary
            ),
            shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.save),
                    style = AppTextStyle.RobotoRegular.sp14.copy(color = Color.White)
                )
            }
        }
    }
}