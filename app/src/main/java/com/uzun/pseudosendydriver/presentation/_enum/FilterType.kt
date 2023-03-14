package com.uzun.pseudosendydriver.presentation._enum

enum class FilterType(val korName: String) {
    ONLY_FIT_TYPE("내 차종만"),
    ONLY_MY_ORDER("내 오더만"),
    EXCEPT_CLOSED("마감 제외")
}