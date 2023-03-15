package com.uzun.pseudosendydriver.presentation.ui

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import com.uzun.pseudosendydriver.presentation.model.OrderItemInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DriverMainViewModel @Inject constructor(

) : ViewModel() {
    private val _orderItemInfoState = MutableStateFlow(emptyList<OrderItemInfo>())
    val orderItemInfoState: StateFlow<List<OrderItemInfo>> = _orderItemInfoState


}