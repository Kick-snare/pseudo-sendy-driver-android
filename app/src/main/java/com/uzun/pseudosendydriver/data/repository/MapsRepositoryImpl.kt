package com.uzun.pseudosendydriver.data.repository

import android.util.Log
import com.uzun.pseudosendydriver.data.model.LngLat
import com.uzun.pseudosendydriver.data.remote.api.MapsApi
import com.uzun.pseudosendydriver.data.remote.dto.RouteUnitEnt
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MapsRepositoryImpl @Inject constructor(
    private val api: MapsApi,
) : MapsRepository {
    override suspend fun getMinifiedAddress(lnglat: LngLat): Result<String> {
        return runCatching {
            api.getReverseGeocoding(lnglat.toCoordString(), "json").body()!!
        }
            .map { res ->
                if (res.status.code == 0)
                    run {
                        val region = res.results.first().region
                        val area1 = region.area1.name
                            .replace("광역", "")
                            .replace("특별", "")
                            .replace("시", "")
                        val area2 = region.area2.name
                        "$area1 $area2"
                    }
                else throw IOException(res.status.message)
            }
    }

    override suspend fun getDrivingRoute(
        start: LngLat,
        goal: LngLat,
        waypoints: List<LngLat>
    ): Result<RouteUnitEnt> {
        return runCatching {
            api.getDrivingRoute(
                start.toCoordString(),
                goal.toCoordString(),
                waypoints.map { it.toCoordString() }.joinToString(separator = "|")
            ).body()!!
        }
            .map { res ->
                if (res.code == 0) res.route.traoptimal.first()
                else throw IOException(res.message)
            }
    }
}