package com.uzun.pseudosendydriver.presentation.model

import com.naver.maps.geometry.LatLng

data class Address(
    val jibunAddress: String = "",
    val roadAddress: String = "",
    val latlng: LatLng = LatLng(0.0, 0.0)
)