package com.uzun.pseudosendydriver

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.uzun.pseudosendydriver.presentation._enum.BottomRoute
import com.uzun.pseudosendydriver.presentation.model.OrderItemInfo
import com.uzun.pseudosendydriver.presentation.ui.common.PseudoSendyDriverRoute
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun rememberPseudoSendyDriverAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberAnimatedNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) = remember(scaffoldState, navController, coroutineScope) {
    PseudoSendyDriverAppState(scaffoldState, navController)
}

class PseudoSendyDriverAppState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController,
) {

    val currentDestination: NavDestination?
        get() = navController.currentDestination

    fun navigateToBottomBarRoute(route: String) {
        if (route != currentDestination?.route) {
            navController.navigate(route) {
                launchSingleTop = true
                restoreState = true
                popUpTo(PseudoSendyDriverRoute.BOTTOM_ROOT.route) {
                    saveState = true
                }
            }
        }
    }

    fun navigateToOrderDetail(orderId: String, from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            navController.navigate("${PseudoSendyDriverRoute.ORDER_DETAIL.route}/$orderId")
        }
    }

    private val bottomBarTabs = BottomRoute.values().map { it.route }
//    private val TopTabs =

    val shouldShowBar: Boolean
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination?.route in bottomBarTabs

//    val shouldShowTopBar: Boolean
//        @Composable get() = navController
//            .currentBackStackEntryAsState().value?.destination?.route in

    fun upPress() { navController.navigateUp() }
}

private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED
