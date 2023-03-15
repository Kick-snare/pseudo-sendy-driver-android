package com.uzun.pseudosendydriver.presentation.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.naver.maps.geometry.LatLng
import com.uzun.pseudosendydriver.presentation._enum.*
import com.uzun.pseudosendydriver.presentation.model.Address
import com.uzun.pseudosendydriver.presentation.model.OrderItemInfo
import com.uzun.pseudosendydriver.presentation.model.SendyTime
import com.uzun.pseudosendydriver.presentation.ui.common.MainBottomNavigation
import com.uzun.pseudosendydriver.presentation.ui.common.MainTopBar
import com.uzun.pseudosendydriver.presentation.ui.orderlist.OrderListScreen
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DriverMainScreen(
    navController: NavHostController = rememberNavController(),
    vm: DriverMainViewModel = hiltViewModel(),
) {
    Scaffold(
        topBar = { MainTopBar() },
        bottomBar = { MainBottomNavigation(navController) }
    ) {
        val orderItemInfo = OrderItemInfo(
            true,
            SendyTime(LocalDateTime.now().plusDays(3)),
            Address("부산광역시 부산진구 서면로 39", "부산광역시 부산진구 서면로 39", LatLng(35.15409, 129.05797)),
            Address("부산광역시 남구 유엔로", "부산광역시 남구 유엔로", LatLng(35.13009, 129.08964)),
            87,
            160_000,
            VehicleType.TRUCK_1T,
            VehicleOption.CARGO,
            TimeOrderTag.DAWN,
            listOf(OrderTag.CAUTION, OrderTag.RIDE_WITH, OrderTag.TIME_IMPORTANT)
        )

        val orderItemInfo2 = OrderItemInfo(
            true,
            SendyTime(LocalDateTime.now().plusDays(3)),
            Address("부산광역시 연제구 연산동 862", "부산광역시 연제로 21", LatLng(35.176391, 129.076994)),
            Address("부산광역시 남구 유엔로", "부산광역시 남구 유엔로", LatLng(35.13009, 129.08964)),
            87,
            160_000,
            VehicleType.TRUCK_1T,
            VehicleOption.CARGO,
            TimeOrderTag.DAWN,
            listOf(OrderTag.CAUTION, OrderTag.RIDE_WITH, OrderTag.TIME_IMPORTANT)
        )

        val orderList = listOf(
            orderItemInfo,
            orderItemInfo2.copy(chargeCost = 122_300),
            orderItemInfo.copy(
                enable = false,
                loadingTime = SendyTime(LocalDateTime.now().minusDays(3)),
                departAddr = Address(
                    "남천동 48-14번지 수영구 부산광역시",
                    "남천동 48-14번지 수영구 부산광역시",
                    LatLng(35.147810, 129.110135)
                ),
                chargeCost = 82_300
            ),
            orderItemInfo2.copy(
                enable = false,
                loadingTime = SendyTime(LocalDateTime.now().minusDays(5)),
                departAddr = Address(
                    "부산광역시 수영구 남천동 48-28",
                    "부산광역시 수영구 남천동 48-28",
                    LatLng(35.123512, 129.042312)
                )
            )
        )
        NavHost(
            route = "bottom-nav-root",
            navController = navController,
            modifier = Modifier
                .padding(top = it.calculateTopPadding())
                .padding(bottom = it.calculateBottomPadding()),
            startDestination = BottomRoute.ORDER_LIST.route
        ) {
            composable(route = BottomRoute.ORDER_LIST.route) {
                OrderListScreen(orderList)
            }
            composable(route = BottomRoute.ORDER_SEARCH.route) {
                OrderSearchScreen(orderList)
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
