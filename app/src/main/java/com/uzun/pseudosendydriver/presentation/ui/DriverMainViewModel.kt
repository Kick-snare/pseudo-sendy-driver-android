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
            LocationInfo("부산 사하구 장림동 1087-2", "부산광역시 사하구 다대로1066번길", LatLng(35.07749993910741, 128.9520781208145)),
            101,
            215_000,
            VehicleType.TRUCK_1T,
            VehicleOption.CARGO,
            TimeOrderTag.DAWN,
            listOf(OrderTag.CAUTION, OrderTag.RIDE_WITH, OrderTag.TIME_IMPORTANT),
            wayPointList = listOf(
                LocationInfo("부산 부산진구 가야동 467","부산광역시 부산진구 엄광로 176",LatLng(35.143619, 129.034922)),
                LocationInfo("부산 사하구 하단동 840","부산광역시 사하구 낙동대로550번길 37",LatLng(35.11557041035922, 128.96797600783964))
            )
        )

        val order2 = OrderFullData(
            "222222",
            true,
            SendyTime(LocalDateTime.now().plusDays(2).plusHours(5).plusMinutes(13)),
            LocationInfo("부산광역시 연제구 연산동 862", "부산광역시 연제로 21", LatLng(35.176391, 129.076994)),
            LocationInfo("부산광역시 남구 유엔로", "부산광역시 남구 유엔로", LatLng(35.13009, 129.08964)),
            87,
            131_000,
            VehicleType.TRUCK_1T,
            VehicleOption.CARGO,
            TimeOrderTag.DAWN,
            listOf(OrderTag.URGE_ALLOCATION, OrderTag.UNLOAD_TOMORROW),
            wayPointList = listOf(
                LocationInfo("부산 부산진구 가야동 467","부산광역시 수영구 호암로30번길 10",LatLng(35.15701587061513, 129.11007811643026)),
            )
        )

        val order3 = order1.copy(
            orderId = "333333",
            enable = true,
            loadingTime = SendyTime(LocalDateTime.now().plusDays(1).plusHours(1).plusMinutes(44)),
            departInfo = LocationInfo(
                "남천동 48-14번지 수영구 부산광역시",
                "남천동 48-14번지 수영구 부산광역시",
                LatLng(35.147810, 129.110135)
            ),
            chargeCost = 82_300,
            orderTagList = listOf(OrderTag.UNLOAD_TOMORROW, OrderTag.TIME_IMPORTANT),
        )

        val order4 = order2.copy(
            orderId = "444444",
            enable = false,
            loadingTime = SendyTime(LocalDateTime.now().minusDays(5).plusHours(5).plusMinutes(13)),
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