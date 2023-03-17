package com.uzun.pseudosendydriver.presentation.ui.orderdetail

import android.util.Log
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naver.maps.geometry.LatLng
import com.uzun.pseudosendydriver.data.model.LngLat
import com.uzun.pseudosendydriver.data.remote.dto.RouteUnitEnt
import com.uzun.pseudosendydriver.data.repository.MapsRepository
import com.uzun.pseudosendydriver.presentation.model.MiniAddresses
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderDetailViewModel @Inject constructor(
    private val mapsRepository: MapsRepository
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    var route by mutableStateOf(RouteUnitEnt())
        private set

    var miniAddresses by mutableStateOf(MiniAddresses())
        private set

    fun getDrivingRoute(
        start : LatLng,
        goal : LatLng,
        waypoints: List<LatLng> = emptyList()
    ) = viewModelScope.launch {
        mapsRepository.getDrivingRoute(
            LngLat(start.longitude, start.latitude),
            LngLat(goal.longitude, goal.latitude),
            waypoints.map { LngLat(it.longitude, it.latitude) }
        )
            .onFailure {
                Log.e("OrderDetailViewModel", it.message ?: "")
                it.printStackTrace()
                _eventFlow.emit(UiEvent.Error("문제가 발생했습니다."))
            }
            .onSuccess { route = it }
    }

    fun getMinifiedAddress(
        latlng: LatLng,
        isArrive : Boolean = true
    ) = viewModelScope.launch {
        mapsRepository.getMinifiedAddress(LngLat(latlng.longitude, latlng.latitude))
            .onFailure {
                Log.e("OrderDetailViewModel", it.message ?: "")
                it.printStackTrace()
                _eventFlow.emit(UiEvent.Error("문제가 발생했습니다."))
            }
            .onSuccess {
                miniAddresses =
                    if(isArrive) miniAddresses.copy(arrive = it)
                    else miniAddresses.copy(depart = it)
            }
    }

    fun onMapClicked() {
        viewModelScope.launch { _eventFlow.emit(UiEvent.FullSizeMap) }
    }

    fun showSnackBar(msg:String, snackBarState: SnackbarHostState) {
        viewModelScope.launch {
            snackBarState.showSnackbar(msg)
        }
    }

    sealed class UiEvent {
        data class Error(val message: String) : UiEvent()
        object FullSizeMap : UiEvent()
    }
}