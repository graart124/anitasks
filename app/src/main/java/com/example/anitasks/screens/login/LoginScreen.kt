package com.example.anitasks.screens.login

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.anitasks.R
import com.example.anitasks.screens.destinations.ScheduleScreenDestination
import com.example.anitasks.screens.destinations.SplashScreenDestination
import com.example.anitasks.ui.theme.AppTextStyle
import com.example.anitasks.ui.theme.Background
import com.example.anitasks.ui.theme.Primary
import com.example.anitasks.ui.theme.PurpleLight
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@ExperimentalMaterialApi
@Destination
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator,
    viewModel: LoginViewModel = hiltViewModel(),
) {


    val snackbarHostState = remember { SnackbarHostState() }

    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        result.data?.let { intent ->
            viewModel.processGoogleSignInResult(intent)
        }
    }
    val loginResult by viewModel.loginResult.collectAsState()

    LaunchedEffect(loginResult) {
        loginResult?.let { success ->
            if (success) {
                navigator.popBackStack()
                navigator.navigate(ScheduleScreenDestination)
            } else {
                snackbarHostState.showSnackbar("Помилка входу\nБудь ласка, спробуйте знову")
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        containerColor = Background,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it.calculateTopPadding()),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.anita_log_in),
                contentDescription = "anita log in",
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            color = PurpleLight.copy(alpha = 0.7f),
                            shape = RoundedCornerShape(7.dp)
                        )
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.log_in_info),
                        style = AppTextStyle.RobotoBold.sp14.copy(
                            color = Color.White,
                            lineHeight = TextUnit(value = 24f, type = TextUnitType.Sp)
                        ),
                    )
                }
                Spacer(modifier = Modifier.height(54.dp))
                GoogleButton(
                    onClick = {
                        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                            .requestEmail()
                            .requestProfile()
                            .build()
                        val gsc = GoogleSignIn.getClient(context, gso)

                        launcher.launch(gsc.signInIntent)
                    }
                )

            }
        }
    }

}

@Composable
fun GoogleButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .fillMaxWidth(),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Primary
        ),
        shape = RoundedCornerShape(7.dp),
        contentPadding = PaddingValues(4.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(6.dp))
                    .padding(12.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_google_icon),//change to google
                    contentDescription = "google icon"
                )
            }
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.sing_in_with_google),
                    style = AppTextStyle.RobotoBold.sp14
                )
            }
        }
    }
}

