package com.uzun.pseudosendydriver.presentation.ui

import android.util.Log
import android.view.Gravity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.naver.maps.map.compose.*
import com.uzun.pseudosendydriver.presentation._const.UIConst
import com.uzun.pseudosendydriver.presentation._enum.FilterType.*
import com.uzun.pseudosendydriver.presentation.model.OrderMarker
import com.uzun.pseudosendydriver.presentation.ui.common.BottomSheet
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

    BottomSheet(
        sheetContent = { onExpanded ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Surface(
                    shape = RoundedCornerShape(2.dp),
                    color = DayGrayscale400,
                    modifier = Modifier.padding(top = UIConst.SPACE_S).size(32.dp, 4.dp)
                ) {}
                OrderListScreen(
                    orderItemList = emptyList(),
                    sortBarEnable =  false,
                    onExpanded = onExpanded
                )
            }
        },
        content = { NaverMapContent() }
    )
}

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun NaverMapContent(
    onExpanded: () -> Unit = {},
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
        modifier = Modifier
            .zIndex(-1f)
            .fillMaxSize(),
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
        locationSource = rememberFusedLocationSource(),
    ) {

    }
}

