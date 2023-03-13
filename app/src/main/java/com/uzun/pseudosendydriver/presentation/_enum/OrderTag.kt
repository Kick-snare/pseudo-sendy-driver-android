package com.uzun.pseudosendydriver.presentation._enum

import com.uzun.pseudosendydriver.R

enum class OrderTag(val korName: String, val iconId: Int) {
    EMERGENCY("긴급", R.drawable.ic_emergency),
    CAUTION("주의", R.drawable.ic_alert_triangle),
    UNLOAD_TOMORROW("야상", R.drawable.ic_one_more_day),
    TIME_IMPORTANT("시간엄수", R.drawable.ic_time_exclamation),
    URGE_ALLOCATION("빠른배차요청", R.drawable.ic_quick),
    RIDE_WITH("동승", R.drawable.ic_riding),
}

enum class TimeOrderTag(val korName: String, val iconId: Int) {
    DAY("", 0),
    NIGHT("야간", R.drawable.ic_night_time),
    DAWN("새벽", R.drawable.ic_morning)
}
