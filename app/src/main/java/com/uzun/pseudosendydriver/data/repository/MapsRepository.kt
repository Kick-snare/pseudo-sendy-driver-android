package com.uzun.pseudosendydriver.data.repository

import com.uzun.pseudosendydriver.data.model.LngLat
import com.uzun.pseudosendydriver.data.remote.dto.RouteUnitEnt

interface MapsRepository {
    suspend fun getMinifiedAddress(lnglat: LngLat) : Result<String>
    suspend fun getDrivingRoute(start: LngLat, goal: LngLat) : Result<RouteUnitEnt>
}