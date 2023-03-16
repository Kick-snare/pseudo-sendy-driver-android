package com.uzun.pseudosendydriver.presentation.ui

import android.graphics.Bitmap
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.naver.maps.geometry.LatLng
import com.uzun.pseudosendydriver.presentation._enum.OrderTag
import com.uzun.pseudosendydriver.presentation._enum.TimeOrderTag
import com.uzun.pseudosendydriver.presentation._enum.VehicleOption
import com.uzun.pseudosendydriver.presentation._enum.VehicleType
import com.uzun.pseudosendydriver.presentation.model.LocationInfo
import com.uzun.pseudosendydriver.presentation.model.OrderFullData
import com.uzun.pseudosendydriver.presentation.model.OrderItemInfo
import com.uzun.pseudosendydriver.presentation.model.SendyTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class DriverMainViewModel @Inject constructor(

) : ViewModel() {
    var orderMap = mutableStateMapOf<String, OrderFullData>()
    var orderList = mutableListOf<OrderItemInfo>()

    init {
        val order1 = OrderFullData(
            "111111",
            true,
            SendyTime(LocalDateTime.now().plusDays(3)),
            LocationInfo("부산광역시 부산진구 서면로 39", "부산광역시 부산진구 서면로 39", LatLng(35.15409, 129.05797)),
            LocationInfo("부산광역시 남구 유엔로", "부산광역시 남구 유엔로", LatLng(35.13009, 129.08964)),
            87,
            160_000,
            VehicleType.TRUCK_1T,
            VehicleOption.CARGO,
            TimeOrderTag.DAWN,
            listOf(OrderTag.CAUTION, OrderTag.RIDE_WITH, OrderTag.TIME_IMPORTANT)
        )

        val order2 = OrderFullData(
            "222222",
            true,
            SendyTime(LocalDateTime.now().plusDays(3)),
            LocationInfo("부산광역시 연제구 연산동 862", "부산광역시 연제로 21", LatLng(35.176391, 129.076994)),
            LocationInfo("부산광역시 남구 유엔로", "부산광역시 남구 유엔로", LatLng(35.13009, 129.08964)),
            87,
            160_000,
            VehicleType.TRUCK_1T,
            VehicleOption.CARGO,
            TimeOrderTag.DAWN,
            listOf(OrderTag.CAUTION, OrderTag.RIDE_WITH, OrderTag.TIME_IMPORTANT)
        )

        val order3 = order1.copy(
            orderId = "333333",
            enable = false,
            loadingTime = SendyTime(LocalDateTime.now().minusDays(3)),
            departInfo = LocationInfo(
                "남천동 48-14번지 수영구 부산광역시",
                "남천동 48-14번지 수영구 부산광역시",
                LatLng(35.147810, 129.110135)
            ),
            chargeCost = 82_300
        )

        val order4 = order2.copy(
            orderId = "444444",
            enable = false,
            loadingTime = SendyTime(LocalDateTime.now().minusDays(5)),
            departInfo = LocationInfo(
                "부산광역시 수영구 남천동 48-28",
                "부산광역시 수영구 남천동 48-28",
                LatLng(35.123512, 129.042312)
            )
        )
        orderMap[order1.orderId] = order1
        orderMap[order2.orderId] = order2
        orderMap[order3.orderId] = order3
        orderMap[order4.orderId] = order4

        orderList = orderMap.toList().map { it.second.toOrderItemInfo() }.toMutableStateList()
    }

}