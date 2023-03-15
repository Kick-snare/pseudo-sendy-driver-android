package com.uzun.pseudosendy.data.remote.dto

import com.uzun.pseudosendydriver.presentation.model.Address

data class Geocode(
    val status: String,
    val errorMessage: String,
    val addresses: List<Address>,
)