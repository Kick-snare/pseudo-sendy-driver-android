package com.uzun.pseudosendydriver.presentation._enum

import com.uzun.pseudosendydriver.R

enum class BottomRoute(
    val route: String,
    val title: String,
    val iconId: Int,
) {
    ORDER_LIST("order-list", "오더목록", R.drawable.icon_solid_list_alt),
    ORDER_SEARCH("order-search", "오더탐색", R.drawable.icon_solid_explore),
    MY_ORDER("my-order", "내 오더", R.drawable.icon_solid_pending),
    CALCULATE_CHARGE("calculate-charge", "정산", R.drawable.icon_solid_coin_alt),
    ACCOUNT_INFO("account_info", "내 정보", R.drawable.icon_solid_profile)
}
