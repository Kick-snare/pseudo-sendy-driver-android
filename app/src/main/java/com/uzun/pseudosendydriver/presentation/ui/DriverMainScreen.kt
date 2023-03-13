package com.uzun.pseudosendydriver.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uzun.pseudosendydriver.presentation._const.UIConst
import com.uzun.pseudosendydriver.presentation._enum.*
import com.uzun.pseudosendydriver.presentation.model.OrderItemInfo
import com.uzun.pseudosendydriver.presentation.model.SendyTime
import com.uzun.pseudosendydriver.presentation.ui.common.MainBottomNavigation
import com.uzun.pseudosendydriver.presentation.ui.common.MainTopBar
import com.uzun.pseudosendydriver.presentation.ui.common.OrderItem
import com.uzun.pseudosendydriver.presentation.ui.orderlist.OrderListScreen
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DriverMainScreen(
    navController: NavHostController = rememberNavController(),
//    vm: OrderFormViewModel = hiltViewModel(),
) {
    Scaffold(
        topBar = { MainTopBar() },
        bottomBar = { MainBottomNavigation(navController) }
    ) {
        NavHost(
            route = "bottom-nav-root",
            navController = navController,
            modifier = Modifier
                .padding(top = it.calculateBottomPadding())
                .padding(bottom = it.calculateBottomPadding()),
            startDestination = BottomRoute.ORDER_LIST.route
        ) {
            composable(route = BottomRoute.ORDER_LIST.route) {
                OrderListScreen()
            }
            composable(route = BottomRoute.ORDER_SEARCH.route) {
                Text("ORDER_SEARCH")
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
        }
    }
}
