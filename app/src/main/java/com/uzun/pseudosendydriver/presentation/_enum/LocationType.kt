package com.uzun.pseudosendydriver.presentation._enum

import com.uzun.pseudosendydriver.R

enum class LocationType(val korName: String, val imageId: Int) {
    LOADING("상차", R.drawable.ic_depart),
    WAYPOINT("경유", R.drawable.ic_waypoint),
    UNLOADING("하차", R.drawable.ic_arrive_semi)
}