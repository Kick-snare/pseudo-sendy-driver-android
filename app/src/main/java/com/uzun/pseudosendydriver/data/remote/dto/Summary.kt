package com.uzun.pseudosendy.data.remote.dto

data class Summary(
    val start: ResponsePositionFormat,
    val goal: ResponsePositionFormat,
    val bbox: List<List<Double>>,
    val distance: Int,
)