package com.uzun.pseudosendydriver.data.remote.dto

import com.naver.maps.geometry.LatLng

data class Summary(
    val start : ResponsePositionFormat = ResponsePositionFormat(),
    val goal : ResponsePositionFormat = ResponsePositionFormat(),
    val waypoints : List<ResponsePositionFormat> = emptyList(),
    val bbox: List<List<Double>> = emptyList(),
    val distance: Int = 0,
) {
    fun getStartLatLng() = LatLng(start.getLatitude(), start.getLongitude())
    fun getGoalLatLng() = LatLng(goal.getLatitude(), goal.getLongitude())
    fun getWaypointLatLngs() = waypoints.map { LatLng(it.getLatitude(), it.getLongitude()) }
    fun getBbox() = Pair(
        LatLng(bbox[0][0], bbox[0][1]),
        LatLng(bbox[1][0], bbox[1][1])
    )
    fun getCenterPoint() = LatLng(
        (bbox[0][1] + bbox[1][1]) / 2,
        (bbox[0][0] + bbox[1][0]) / 2,
    )
}