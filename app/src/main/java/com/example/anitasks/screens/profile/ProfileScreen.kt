package com.example.anitasks.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.anitasks.R
import com.example.anitasks.ui.components.TopAppBar
import com.example.anitasks.ui.theme.AppTextStyle
import com.example.anitasks.ui.theme.NavBarColor
import com.example.anitasks.ui.theme.Primary
import com.example.anitasks.ui.theme.PurpleDark
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsState().value
    val placeholderImage = painterResource(id = R.drawable.anita_welcome)
    Scaffold(
        topBar = { TopAppBar(label = stringResource(R.string.profile)) }
    ) {
        Column(
            modifier = Modifier.padding(
                top = it.calculateTopPadding(),
                bottom = it.calculateBottomPadding()
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .padding(top = 8.dp)
                    .background(color = PurpleDark, shape = RoundedCornerShape(8.dp))
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Box(modifier = Modifier.size(100.dp), contentAlignment = Alignment.Center) {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .offset(x = 6.dp)
                            .background(color = NavBarColor, shape = CircleShape)
                    )
                    AsyncImage(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape),
                        model = state.user?.photoUrl,
                        contentDescription = "user photo",
                        placeholder = placeholderImage,
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.width(34.dp))
                Column {
                    //texts
                    TextInfo(label = state.user?.displayName)
                }
            }
        }
    }
}

@Composable
fun TextInfo(
    label: String?
) {
    Box(
        modifier = Modifier
            .background(color = Primary, shape = RoundedCornerShape(7.dp))
            .padding(horizontal = 10.dp, vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label?:"",
            style = AppTextStyle.RobotoRegular.sp36.copy(color = Color.White),
        )
    }
}