package com.uzun.pseudosendydriver.presentation._enum

import com.uzun.pseudosendydriver.R

enum class TabType(val korName: String, val iconId: Int) {
    RECOMMENDED("추천오더", R.drawable.badge_order_status_received),
    COMMON("일반오더", 0)
}