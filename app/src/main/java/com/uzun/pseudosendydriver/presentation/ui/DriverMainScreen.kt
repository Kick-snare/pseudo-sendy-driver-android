package com.uzun.pseudosendydriver.presentation.ui

import androidx.compose.animation.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.uzun.pseudosendydriver.PseudoSendyDriverAppState
import com.uzun.pseudosendydriver.presentation._enum.*
import com.uzun.pseudosendydriver.presentation.ui.common.MainBottomNavigation
import com.uzun.pseudosendydriver.presentation.ui.common.MainTopBar
import com.uzun.pseudosendydriver.presentation.ui.common.PseudoSendyDriverRoute
import com.uzun.pseudosendydriver.presentation.ui.orderdetail.OrderDetailScreen
import com.uzun.pseudosendydriver.presentation.ui.orderlist.OrderListScreen
import com.uzun.pseudosendydriver.presentation.ui.ordersearch.OrderSearchScreen
import com.uzun.pseudosendydriver.rememberPseudoSendyDriverAppState

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DriverMainScreen(
    appState: PseudoSendyDriverAppState = rememberPseudoSendyDriverAppState(),
    vm: DriverMainViewModel = hiltViewModel(),
) {
    Scaffold(
        topBar = {
            AnimatedVisibility(
                visible = appState.shouldShowBar,
                enter = slideInVertically() + fadeIn(),
                content = { MainTopBar() }
            )
        },
        bottomBar = {
            AnimatedVisibility(
                visible = appState.shouldShowBar,
                enter = slideInVertically { it / 2 } + fadeIn(),
                exit = slideOutVertically { it / 2 } + fadeOut(),
                content = { MainBottomNavigation(appState.navController) }
            )
        },
        scaffoldState = appState.scaffoldState,

        ) { paddingValues ->

        AnimatedNavHost(
            route = PseudoSendyDriverRoute.BOTTOM_ROOT.route,
            navController = appState.navController,
            startDestination = BottomRoute.ORDER_LIST.route
        ) {
            composable(route = BottomRoute.ORDER_LIST.route) { navBackStackEntry ->
                OrderListScreen(
                    orderItemList = vm.orderList,
                    moveToOrderDetail = { appState.navigateToOrderDetail(it, navBackStackEntry) },
                    paddingValues = paddingValues,
                    sortBy = vm::sortBy
                )
            }
            composable(route = BottomRoute.ORDER_SEARCH.route) { navBackStackEntry ->
                OrderSearchScreen(
                    vm.orderList,
                    moveToOrderDetail = { appState.navigateToOrderDetail(it, navBackStackEntry) },
                    paddingValues = paddingValues
                )
            }
            composable(route = BottomRoute.MY_ORDER.route) {
                Text("MY_ORDER")
            }
            composable(route = BottomRoute.CALCULATE_CHARGE.route) {
                Text("CALCULATE_CHARGE")
            }
            composable(route = BottomRoute.ACCOUNT_INFO.route) {
                Text("ACCOUNT_INFO")
            }

            composable(
                route = "${PseudoSendyDriverRoute.ORDER_DETAIL.route}/{orderId}",
                arguments = listOf(navArgument("orderId") { type = NavType.StringType }),
                enterTransition = { slideInVertically { it / 2 } + fadeIn() },
                exitTransition = { slideOutVertically { it / 2 } + fadeOut() },
                popExitTransition = { slideOutVertically { it / 2 } + fadeOut() },
            ) {
                val orderId = requireNotNull(it.arguments).getString("orderId")
                val selectedOrder = vm.orderMap[orderId]!!

                OrderDetailScreen(
                    popUP = appState::upPress,
                    orderInfo = selectedOrder,
                    toFullSizeMap = {},
                    snackBarState = appState.scaffoldState.snackbarHostState
                )
            }
        }
    }
}