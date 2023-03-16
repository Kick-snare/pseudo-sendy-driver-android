package com.uzun.pseudosendydriver.presentation.model

import com.naver.maps.geometry.LatLng

data class LocationInfo(
    val jibunAddress: String = "",
    val roadAddress: String = "",
    val latlng: LatLng = LatLng(0.0, 0.0),
    val detailAddr: String = "",
    val locationOption: String = ""
)