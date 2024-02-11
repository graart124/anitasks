@file:OptIn(ExperimentalMaterialApi::class)

package com.example.anitasks.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.anitasks.ui.theme.AppTextStyle
import com.example.anitasks.ui.theme.PurpleDark
import com.example.anitasks.ui.theme.UnselectedNavBarItemColor


@Composable
fun TextFieldWithIcon(
    title: String,
    value: String?,
    hint: String,
    @DrawableRes iconId: Int,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions? = null
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = PurpleDark, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 4.dp, vertical = 6.dp)
    ) {
        Text(text = title, style = AppTextStyle.RobotoBold.sp14.copy(color = Color.White))
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(0.5.dp, UnselectedNavBarItemColor), RoundedCornerShape(8.dp))
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BasicTextField(
                value = value ?: "",
                modifier = Modifier.weight(1f),
                onValueChange = onValueChange,
                textStyle = AppTextStyle.RobotoRegular.sp14.copy(color = Color.White),
                maxLines = 1,
                keyboardOptions = keyboardOptions?:KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true,
                decorationBox = { innerTextField ->
                    if (value.isNullOrEmpty()) {
                        Text(
                            text = hint,
                            style = AppTextStyle.RobotoRegular.sp14.copy(color = Color.Gray)
                        )
                    }
                    innerTextField()
                }
            )
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = iconId),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
    }
}