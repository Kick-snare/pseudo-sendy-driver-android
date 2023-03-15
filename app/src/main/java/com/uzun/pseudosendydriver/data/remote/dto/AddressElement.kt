package com.uzun.pseudosendy.data.remote.dto

data class AddressElement(
    val code: String,
    val longName: String,
    val shortName: String,
    val types: List<String>
)