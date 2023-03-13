package com.uzun.pseudosendydriver.presentation.model

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*
import kotlin.time.DurationUnit

data class SendyTime (
    var value: LocalDateTime = LocalDateTime.now(),
) {
    constructor(timeStamp: Long) : this() {
        val triggerTime = LocalDateTime.ofInstant(
            Instant.ofEpochMilli(timeStamp), TimeZone.getDefault().toZoneId()
        )
        value = triggerTime
    }

    override fun toString(): String {
        return value.format(DateTimeFormatter.ofPattern("MM.dd(${value.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREA)}) - HH:mm"))
    }

    fun getDayLeft() : Int {
        return Period.between(LocalDate.now(), value.toLocalDate()).days
    }
}
