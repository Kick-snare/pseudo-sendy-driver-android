package com.uzun.pseudosendydriver.presentation.model

import com.uzun.pseudosendydriver.presentation._enum.OrderTag
import com.uzun.pseudosendydriver.presentation._enum.TimeOrderTag
import com.uzun.pseudosendydriver.presentation._enum.VehicleOption
import com.uzun.pseudosendydriver.presentation._enum.VehicleType

data class OrderFullData(
    val orderId: String = "",
    val enable: Boolean = false,
    val loadingTime : SendyTime = SendyTime(),
    val departInfo : LocationInfo = LocationInfo(),
    val arriveInfo: LocationInfo = LocationInfo(),
    val distance: Int = 0,
    val chargeCost: Int = 0,
    val vehicleType : VehicleType = VehicleType.UNKNOWN,
    val vehicleOption: VehicleOption = VehicleOption.UNKNOWN,
    val timeOrderTag: TimeOrderTag = TimeOrderTag.DAY,
    val orderTagList: List<OrderTag> = emptyList(),

    // plus
    val wayPointList: List<LocationInfo> = emptyList(),
    val rideWith: Int = 0,
    val rideCost: Int = 0,
    val adminAnnounce: String = "",
    val clientRequestDescription : String = "",

) {
    fun getVehicleDescriptionString() =
        "${vehicleType.korName} ${vehicleOption.korName}"

    fun toOrderItemInfo() = OrderItemInfo(
        orderId = orderId,
        enable = enable,
        loadingTime = loadingTime,
        departInfo = departInfo,
        arriveInfo = arriveInfo,
        distance = distance,
        chargeCost = chargeCost,
        vehicleType = vehicleType,
        vehicleOption = vehicleOption,
        timeOrderTag = timeOrderTag,
        orderTagList = orderTagList,
    )

    fun getLoadId() = loadingTime.toStringInLoadId()+orderId
}
