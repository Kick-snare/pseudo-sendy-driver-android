package com.uzun.pseudosendydriver.presentation.model

import com.uzun.pseudosendydriver.presentation._enum.FilterType

data class FilterSelection(
    val filter: FilterType,
    val enable: Boolean
)
