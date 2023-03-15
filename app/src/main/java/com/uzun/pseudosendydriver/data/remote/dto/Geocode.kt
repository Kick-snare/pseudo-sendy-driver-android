package com.uzun.pseudosendy.data.remote.dto

import com.uzun.pseudosendydriver.presentation.model.LocationInfo

data class Geocode(
    val status: String,
    val errorMessage: String,
    val locationInfos: List<LocationInfo>,
)