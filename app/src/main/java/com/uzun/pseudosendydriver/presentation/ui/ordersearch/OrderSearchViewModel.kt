package com.uzun.pseudosendydriver.presentation.ui.ordersearch

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.compose.CameraPositionState
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.uzun.pseudosendydriver.presentation._enum.FilterType
import com.uzun.pseudosendydriver.presentation.model.FilterSelection
import com.uzun.pseudosendydriver.presentation.model.OrderItemInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.logging.Filter
import javax.inject.Inject

@HiltViewModel
class OrderSearchViewModel @Inject constructor(

) : ViewModel() {

    var cameraPositionState by mutableStateOf(
        CameraPositionState(
            position = CameraPosition(
                LatLng(35.154318, 129.057384),
                10.0
            )
        )
    )
        private set

    var selectedOrder by mutableStateOf(OrderItemInfo())
        private set

    var miniOrderBoxVisibility by mutableStateOf(false)
        private set

    var filterMap = mutableStateMapOf<FilterType, Boolean>()
        private set

    init{
        FilterType.values().forEach{ filterMap[it] = false }
    }

    @OptIn(ExperimentalNaverMapApi::class)
    fun onMarkerClicked(orderItemInfo: OrderItemInfo) {
        viewModelScope.launch {
            cameraPositionState.animate(
                update = CameraUpdate.scrollTo(
                    orderItemInfo.departAddr.latlng
                )
            )
        }
        miniOrderBoxVisibility = true
        selectedOrder = orderItemInfo
    }

    fun onMapClicked() { miniOrderBoxVisibility = false }

    fun onFilterSelected(filterType: FilterType) {
        filterMap[filterType] = !(filterMap[filterType] ?: false)
    }



}