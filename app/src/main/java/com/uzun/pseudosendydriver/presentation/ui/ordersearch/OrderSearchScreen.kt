package com.uzun.pseudosendydriver.presentation.ui.ordersearch

import android.view.Gravity
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.naver.maps.map.compose.*
import com.naver.maps.map.overlay.OverlayImage
import com.uzun.pseudosendydriver.R
import com.uzun.pseudosendydriver.presentation._const.UIConst
import com.uzun.pseudosendydriver.presentation._enum.FilterType.*
import com.uzun.pseudosendydriver.presentation.model.OrderItemInfo
import com.uzun.pseudosendydriver.presentation.ui.common.BottomSheet
import com.uzun.pseudosendydriver.presentation.ui.common.OrderItem
import com.uzun.pseudosendydriver.presentation.ui.orderlist.OrderListScreen
import com.uzun.pseudosendydriver.presentation.ui.theme.*
import com.uzun.pseudosendydriver.presentation.util.toCommaFormat
import java.text.DecimalFormat

@Composable
fun OrderSearchScreen(
    orderItemList: List<OrderItemInfo>,
    viewModel: OrderSearchViewModel = hiltViewModel(),
    paddingValues: PaddingValues,
) = Column(
    modifier = Modifier
        .fillMaxSize()
        .padding(top = paddingValues.calculateTopPadding())
        .padding(bottom = paddingValues.calculateBottomPadding()),
) {
    FilterBar(
        filterEnable = viewModel.filterMap,
        onFilterSelected = viewModel::onFilterSelected
    )

    BottomSheet(
        sheetContent = { onExpanded ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                handleBar()
                OrderListScreen(
                    orderItemList = orderItemList,
                    sortBarEnable = false,
                    onExpanded = onExpanded,
                    paddingValues = paddingValues
                )
            }
        },
        content = {
            NaverMapContent(
                orderItemList = orderItemList,
                selectedOrder = viewModel.selectedOrder,
                miniOrderBoxVisibility = viewModel.miniOrderBoxVisibility,
                cameraPositionState = viewModel.cameraPositionState,
                onMarkerClicked = viewModel::onMarkerClicked,
                onMapClicked = viewModel::onMapClicked
            )
        }
    )
}

@Composable
fun handleBar() = Surface(
    shape = RoundedCornerShape(2.dp),
    color = DayGrayscale400,
    modifier = Modifier
        .padding(top = UIConst.SPACE_S)
        .size(32.dp, 4.dp)
) {}

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun NaverMapContent(
    orderItemList: List<OrderItemInfo>,
    selectedOrder: OrderItemInfo? = null,
    miniOrderBoxVisibility: Boolean = false,
    onSelectedOrderClicked: () -> Unit = {},
    cameraPositionState: CameraPositionState = rememberCameraPositionState(),
    onMarkerClicked: (OrderItemInfo) -> Unit = {},
    onMapClicked: () -> Unit = {},
) {
    Box {
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(UIConst.SPACE_XL)
                .padding(bottom = 56.dp)
        ) {
            AnimatedVisibility(
                visible = miniOrderBoxVisibility,
                enter = slideInVertically { it/2 } + fadeIn(),
                exit = slideOutVertically { it/2 } + fadeOut()
            ) {
                OrderItem(
                    orderItemInfo = selectedOrder!!,
                    isMiniMode = true,
                    onClick = onSelectedOrderClicked
                )
            }
        }

        NaverMap(
            modifier = Modifier
                .zIndex(-1f)
                .fillMaxSize(),
            properties = MapProperties(
                maxZoom = 18.0,
                minZoom = 5.0,
                isLiteModeEnabled = true,
            ),
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
            cameraPositionState = cameraPositionState,
            onMapClick = { _, _ -> onMapClicked.invoke() }
        ) {

            orderItemList.forEach {
                Marker(
                    state = MarkerState(position = it.departInfo.latlng),
                    icon = OverlayImage.fromResource(
                        if(it.loadingTime.getDayLeft() > 0) R.drawable.icon_price_tag_stop
                        else R.drawable.icon_price_tag_thunder
                    ),
                    width = 102.dp,
                    height = 41.dp,
                    captionText = "\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0" +
                        it.chargeCost.toCommaFormat() + "₩",
                    captionTextSize = 16.sp,
                    captionAligns = arrayOf(Align.Top),
                    captionOffset = (-24).dp,
                    onClick = { marker ->
                        onMarkerClicked(it)
                        true
                    }
                )
            }
        }
    }
}

@Composable
fun OrderMarker(
    orderItemInfo: OrderItemInfo,
) = Row(verticalAlignment = Alignment.CenterVertically) {
    Icon(
        painterResource(id = R.drawable.badge_order_status_received),
        contentDescription = null
    )
    Text(
        text = "${DecimalFormat("#,###,###").format(orderItemInfo.chargeCost)}W",
        style = PseudoSendyTheme.typography.Normal.copy(color = Black)
    )
}

