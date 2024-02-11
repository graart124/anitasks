package com.example.anitasks.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.anitasks.R
import com.example.anitasks.screens.NavGraphs
import com.example.anitasks.screens.appCurrentDestinationAsState
import com.example.anitasks.screens.destinations.AddLessonScreenDestination
import com.example.anitasks.screens.destinations.Destination
import com.example.anitasks.screens.destinations.ExamsListScreenDestination
import com.example.anitasks.screens.destinations.ProfileScreenDestination
import com.example.anitasks.screens.destinations.ScheduleScreenDestination
import com.example.anitasks.screens.startAppDestination
import com.example.anitasks.ui.theme.AppTextStyle
import com.example.anitasks.ui.theme.NavBarColor
import com.example.anitasks.ui.theme.SelectedNavBarItemColor
import com.example.anitasks.ui.theme.UnselectedNavBarItemColor


@ExperimentalMaterialApi
@Composable
fun BottomNavigationBar(
    navController: NavController
) {

    val currentDestination: Destination = navController.appCurrentDestinationAsState().value
        ?: NavGraphs.root.startAppDestination
    val items = listOf(
        BottomNavigationItem.Schedule,
        BottomNavigationItem.AddLesson,
        BottomNavigationItem.Session,
        BottomNavigationItem.Profile
    )
    if (items.any { it.destination == currentDestination }) {
        BottomAppBar(
            modifier = Modifier.height(56.dp),
            containerColor = NavBarColor,
            contentPadding = PaddingValues(0.dp)
        ) {
            items.forEach { bottomNavItem ->
                val isSelected = bottomNavItem.destination == currentDestination
                BottomNavItem(
                    modifier = Modifier.weight(1f),
                    bottomNavItem,
                    isSelected,
                    onClick = {
                        if (!isSelected) {
                            navController.popBackStack()
                            navController.navigate(bottomNavItem.destination.route)
                        }
                    }
                )
            }
        }
    }
}

@Composable
@ExperimentalMaterialApi
private fun BottomNavItem(
    modifier: Modifier = Modifier,
    item: BottomNavigationItem,
    isSelected: Boolean,
    onClick: (BottomNavigationItem) -> Unit
) {
    val contentColor = if (isSelected) SelectedNavBarItemColor else UnselectedNavBarItemColor

    Column(
        modifier = modifier
            .fillMaxHeight()
            .clickable {
                onClick(item)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.size(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = item.icon),
                contentDescription = "icon",
                tint = contentColor
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Text(
            text = item.title, style = AppTextStyle.RobotoRegular.sp12.copy(color = contentColor)
        )
    }
}


@ExperimentalMaterialApi
sealed class BottomNavigationItem(val destination: Destination, val icon: Int, val title: String) {
    data object Schedule :
        BottomNavigationItem(ScheduleScreenDestination, R.drawable.ic_bottom_calendar, "Розклад")

    data object AddLesson :
        BottomNavigationItem(AddLessonScreenDestination, R.drawable.ic_bottom_add_lesson, "Нове заняття")
    data object Session :
        BottomNavigationItem(ExamsListScreenDestination, R.drawable.ic_bottom_exams, "Сесія")
    data object Profile :
        BottomNavigationItem(ProfileScreenDestination, R.drawable.ic_bottom_profile, "Профіль")
}