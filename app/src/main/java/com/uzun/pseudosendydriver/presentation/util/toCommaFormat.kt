package com.uzun.pseudosendydriver.presentation.util

import java.text.DecimalFormat

fun Number.toCommaFormat() : String =
    DecimalFormat("#,###,###").format(this)
