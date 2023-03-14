package com.uzun.pseudosendydriver.presentation.ui

import android.util.Log
import android.view.Gravity
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.naver.maps.map.LocationSource
import com.naver.maps.map.UiSettings
import com.naver.maps.map.compose.*
import com.uzun.pseudosendydriver.presentation._enum.FilterType.*
import com.uzun.pseudosendydriver.presentation.model.OrderMarker
import com.uzun.pseudosendydriver.presentation.ui.common.ModalBottomSheet
import com.uzun.pseudosendydriver.presentation.ui.orderlist.OrderListScreen
import com.uzun.pseudosendydriver.presentation.ui.ordersearch.FilterBar
import com.uzun.pseudosendydriver.presentation.ui.theme.*

@Composable
fun OrderSearchScreen() = Column(
    modifier = Modifier.fillMaxSize()
) {
    FilterBar(
        filterEnable = mapOf(ONLY_FIT_TYPE to true, ONLY_MY_ORDER to false, EXCEPT_CLOSED to false)
    ) {
        Log.d("TEST", "${it.korName} selected!")
    }

    ModalBottomSheet(
        sheetContent = { OrderListScreen(false) },
        activityContentScope = { onHalfExpanded -> NaverMapContent(onHalfExpanded) }
    )
}

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun NaverMapContent(
    onHalfExpanded: () -> Unit,
    orderMarkerList: List<OrderMarker> = emptyList()
) {
    val mapProperties by remember {
        mutableStateOf(
            MapProperties(
                maxZoom = 10.0,
                minZoom = 5.0,
                isLiteModeEnabled = true,
            )
        )
    }

    NaverMap(
        modifier = Modifier.fillMaxSize(),
        properties = mapProperties,
        uiSettings = MapUiSettings(
            isTiltGesturesEnabled = false,
            isCompassEnabled = false,
            isZoomControlEnabled = false,
            isRotateGesturesEnabled = false,
            isScaleBarEnabled = false,
            isLocationButtonEnabled = true,
            logoGravity = Gravity.TOP or Gravity.START
        ),
        locationSource = rememberFusedLocationSource()
    ) {

    }
}

