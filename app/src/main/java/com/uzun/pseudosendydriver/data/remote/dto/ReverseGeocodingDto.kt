package com.uzun.pseudosendydriver.data.remote.dto

data class ReverseGeocodingDto(
    val results: List<Result>,
    val status: Status
)