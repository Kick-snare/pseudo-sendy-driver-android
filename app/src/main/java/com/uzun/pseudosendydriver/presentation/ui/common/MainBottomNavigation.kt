package com.uzun.pseudosendydriver.presentation.ui.common

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.uzun.pseudosendydriver.presentation._enum.BottomRoute
import com.uzun.pseudosendydriver.presentation.ui.theme.DayGrayscale100
import com.uzun.pseudosendydriver.presentation.ui.theme.DayGrayscale400
import com.uzun.pseudosendydriver.presentation.ui.theme.PseudoSendyTheme
import com.uzun.pseudosendydriver.presentation.ui.theme.White

@Composable
fun MainBottomNavigation(
    navController: NavHostController,
) = BottomNavigation(
    backgroundColor = White,
    elevation = 16.dp
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomRoute.values().forEach { screen ->
        val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
        BottomNavigationItem(
            icon = {
                Icon(
                    painterResource(id = screen.iconId),
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = screen.title,
                    style = PseudoSendyTheme.typography.XS.copy(
                        if(selected) DayGrayscale100 else DayGrayscale400
                    ),
                )
            },
            selected = selected,
            alwaysShowLabel = true,
            onClick = {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            selectedContentColor = DayGrayscale100,
            unselectedContentColor = DayGrayscale400
        )
    }
}